drop sequence transaction_iid;
drop table kyc;
drop table login;
drop table transaction_table;
drop table failed_wallet_trans;
drop table bussiness_login;
drop table merchant_details;
drop table account_details;

create table account_details(
user_name varchar2(100) not null,
mobile_no number(10),
email_id varchar2(100) not null,
account_no number(10) not null,
balance decimal(18,2) not null,
account_status varchar2(15) default 'Active',
constraint user_pk unique(user_name),
constraint mob_uq primary key(mobile_no),
constraint mob_no_chk check( 6000000000<=mobile_no and mobile_no<=9999999999),
constraint email_uq unique(email_id),
constraint acc_no_pk unique(account_no),
constraint acc_no_chk check( 1000000000<=account_no and account_no<=9999999999)
);

create table login(
mobile_no number(10) not null,
upi_passwd number(4) not null,
status number default 1,
constraint upi_pass_chk check(1000<=upi_passwd and upi_passwd<=9999),
constraint mob_for foreign key(mobile_no) references account_details(mobile_no)
);

create table bussiness_login(
mobile_no number(10) not null,
merchant_id varchar2(50) not null,
account_num number(10) not null,
status number default 1,
constraint mob_fk foreign key(mobile_no) references account_details(mobile_no),
constraint acc_num_pk unique(account_num),
constraint acc_num_chk check( 1000000000<=account_num and account_num<=9999999999)
);

create table transaction_table(
mobile_no number(10) not null,
rec_mob_no number(10) not null,
categories varchar2(50),
transaction_time timestamp default sysdate,
transaction_amount number not null,
status varchar2(50) default 'Success',
comments varchar2(100),
constraint amount_ck check(transaction_amount>0),
constraint category_ck check(categories in ('Credited','Debited','Failed')),
constraint transaction_chk check(status in ('Success','Failure')),
constraint mobi_fk foreign key(mobile_no) references account_details(mobile_no)
);

create table kyc(
mobile_no number(10) not null,
aadhar_no varchar(12),
kyc_username varchar2(50),
passport_no varchar2(8),
driving_license_no varchar2(15),
kyc_status varchar2(1) default 'N',
kyc_wallet number,
constraint aadhar_no_uq unique(aadhar_no),
constraint passport_no_uq unique(passport_no),
constraint driving_no_uq unique(driving_license_no),
constraint mobile_fo_k foreign key(mobile_no) references account_details(mobile_no),
constraint kyc_chk check (kyc_status in ('Y','N'))
);

create table failed_wallet_trans(
mobile_no number(10) not null,
rec_mob_no number(10) not null,
categories varchar2(50) default 'Failed',
transaction_time timestamp default sysdate,
transaction_amount number not null,
transaction_status varchar2(50) default 'Failure'
);

create table merchant_details(
transaction_id number not null,
merchant_id varchar2(50) not null,
mobile_no number(10) not null,
amount decimal(18,2) not null,
comments varchar2(10) default 'PAID',
transaction_time timestamp default sysdate,
constraint mob_no_ck check( 6000000000<=mobile_no and mobile_no<=9999999999),
constraint mob_fr_k foreign key(mobile_no) references account_details(mobile_no),
constraint comments_ck check (comments in ('PAID','REFUND'))
);

create sequence transaction_iid increment by 1;

create or replace procedure register_loginpersonal (
m_mobile_no in number,
passwd in number,
login_stats out varchar2)
as 
verify_sts number;
refer number;
begin
  verify_sts := mob_fn(m_mobile_no);
    if verify_sts=1 then
        select count(mobile_no) into refer from login where mobile_no=m_mobile_no;
            if(refer=0) then
                insert into login(mobile_no,upi_passwd) values (m_mobile_no,passwd);
                insert into kyc(mobile_no,kyc_wallet) values(m_mobile_no,50);
                login_stats := 'Account created';
                commit;
            else
                login_stats := 'Account already exists';
            end if;
    end if;
exception when others then
login_stats := 'Registered mobile number is not available. Contact Bank';
end register_loginpersonal;
/
declare
login_stats varchar2(100);
begin
register_loginpersonal(9894983673,1000,login_stats);
dbms_output.put_line(login_stats);
end;

create or replace procedure register_bussiness(
mob_no in number,
merchnt_id in varchar2,
acc_no in number,
login_stats out varchar2
) as 
refer number;
verify_sts number;
begin
    verify_sts := mob_fn(mob_no);
    if verify_sts=1 then
        select count(account_no) into refer from account_details where account_no=acc_no and account_status='Active';
        if(refer=1) then
            insert into bussiness_login(merchant_id,mobile_no,account_num) values(merchnt_id,mob_no,acc_no);
            login_stats := 'Account created';
            commit;
        else
            login_stats := 'Invalid account number';
            end if;
    end if;
exception when others then
login_stats := 'Registered mobile number is not available. Contact Bank';
end register_bussiness;

declare
login_stats varchar2(100);
begin
register_bussiness(8778621280,'IRCTC',5000000010,login_stats);
dbms_output.put_line(login_stats);
end;

create or replace procedure login_personal(
m_mobile_no in number,
passwd in number,
login_stats out varchar2)as
passchk number;
begin
    select upi_passwd into passchk from login where mobile_no = m_mobile_no and status=1;
    if(passchk=passwd) then
        login_stats := 'Account logged-in';
    else
        login_stats := 'Incorrect username/password';
    end if;
exception when others then
    login_stats := 'Incorrect username/password';
end login_personal;



create or replace procedure mob_chk_proc(
mob_no in number,
mob_verification out number
) as 
verification number:=0;
begin
    select count(mobile_no) into verification from login where mobile_no=mob_no;
    if(verification=0) then
        mob_verification:=1;
    else
        mob_verification:=0;
     end if;   
end mob_chk_proc;

create or replace procedure mob_new(
mob_no in number,
mob_verification out number
) as 
verification number:=0;
begin
    select count(mobile_no) into verification from bussiness_login where mobile_no=mob_no;
    if(verification=0) then
        mob_verification:=1;
    else
        mob_verification:=0;
     end if;   
end mob_new;

create or replace procedure wallet_proc(
sender_mob in number,
receiver_mob in number,
transfer_amount in number,
comments_s in varchar,
transaction_sts out varchar
) as 
confirmation number;
confirmation1 number;
final_check number;
balance_amnt number;
begin
    if (transfer_amount>0) then
    select kyc_wallet into balance_amnt from kyc where mobile_no=sender_mob;
        if(balance_amnt>=transfer_amount) then
            update kyc set kyc_wallet = kyc_wallet-transfer_amount where mobile_no=sender_mob;
            update kyc set kyc_wallet = kyc_wallet+transfer_amount where mobile_no=receiver_mob;
            insert into transaction_table(mobile_no,rec_mob_no,categories,transaction_amount,status,comments) values
                (sender_mob,receiver_mob,'Debited',transfer_amount,'Success',comments_s);
            insert into transaction_table(mobile_no,rec_mob_no,categories,transaction_amount,status,comments) values
                (receiver_mob,sender_mob,'Credited',transfer_amount,'Success',comments_s);
            transaction_sts:='Transaction Successfull';
            commit;
        else
            transaction_sts:='Not sufficient balance';
            end if;
  else  
    transaction_sts:='Transaction Failed';
    rollback;
    insert into failed_wallet_trans(mobile_no,rec_mob_no,transaction_amount) values (sender_mob,receiver_mob,transfer_amount);
    commit;
end if;
end wallet_proc;

create or replace procedure KYC_PROC(
kyc_mobile in number,
kyc_aadhar in varchar,
kyc_passport in varchar,
kyc_driving in varchar,
kycc_username in varchar,
kyc_sts out varchar)
as
count_verification number;
verification number;
begin
    verification:=kyc_status_chk(kyc_mobile);
    if(verification=0) then
        if((kyc_aadhar is not null) and (kyc_passport is null) and (kyc_driving is null)) then
            update kyc set kyc_username=kycc_username,aadhar_no=kyc_aadhar,kyc_status='Y' where mobile_no=kyc_mobile;
            kyc_sts:='Kyc updated';
            commit;        
        elsif((kyc_aadhar is null) and (kyc_passport is not null) and (kyc_driving is null)) then
            update kyc set kyc_username=kycc_username,passport_no=kyc_passport,kyc_status='Y' where mobile_no=kyc_mobile;
            kyc_sts:='Kyc updated';
            commit;    
        elsif((kyc_aadhar is null) and (kyc_passport is null) and (kyc_driving is not null)) then
            update kyc set kyc_username=kycc_username,driving_license_no=kyc_driving,kyc_status='Y' where mobile_no=kyc_mobile;
            kyc_sts:='Kyc updated';
            commit;    
        end if;
    else
        kyc_sts:='Kyc already exists.'; 
    end if;
exception when others then
    kyc_sts:='Kyc already exists.Cannot be updated'; 
end KYC_PROC;

declare
kyc_sts varchar2(50);
begin
kyc_proc(9894983673,null,null,'502732750194789','Karthi',kyc_sts);
dbms_output.put_line(kyc_sts);
end;

create or replace procedure account_to_wallet(
account_num in number,
mobile_num in number,
transfer_amount in number,
comments_s in varchar,
transaction_sts out varchar
)as 
balance_amnt number;
acc_num number;
mob_no number;
begin
        select mobile_no into mob_no from account_details where account_no=account_num and account_status='Active';
        if(mob_no=mobile_num) then
            select balance into balance_amnt from account_details where account_no=account_num;
                if(balance_amnt>=transfer_amount) then
                    update kyc set kyc_wallet = kyc_wallet+transfer_amount where mobile_no=mobile_num;
                    update account_details set balance=balance-transfer_amount where account_no=account_num;
                    insert into transaction_table(mobile_no,rec_mob_no,categories,transaction_amount,status,comments) values
                    (mobile_num,account_num,'Credited',transfer_amount,'Success',comments_s);
                    transaction_sts:='Transaction Successfull';
                    commit;
                else
                    transaction_sts:='Not sufficient balance-Transaction Failed';
                end if;
        else
            transaction_sts:='Account number not valid-Transaction Failed';
        end if;
exception when others then
  transaction_sts:='Transaction Failed';
  rollback;
  insert into transaction_table(mobile_no,rec_mob_no,categories,transaction_amount,status,comments) values
    (mobile_num,account_num,'Failed',transfer_amount,'Failure',comments_s);
  
end account_to_wallet;

create or replace procedure pay_to_merchant(
merchantid in varchar,
mobile_num in number,
amount in number,
status out varchar,
trans_id out number)
as 
acc_no number;
balance_amnt number;
iid number;
begin
    select account_num into acc_no from bussiness_login where merchant_id=merchantid and status=1;
    select kyc_wallet into balance_amnt from kyc where mobile_no=mobile_num;
    select transaction_iid.nextval into iid from dual;
    if(balance_amnt>=amount) then
        update kyc set kyc_wallet = kyc_wallet-amount where mobile_no=mobile_num;
        update account_details set balance=balance+amount where account_no=acc_no;
        insert into merchant_details(transaction_id,merchant_id,mobile_no,amount,comments) values (iid,merchantid,mobile_num,amount,'PAID');
        status:='Transaction Successfull';
        trans_id:=iid;
        commit;
    else
        status:='Not sufficient balance-Transaction Failed';
        trans_id:=null;
    end if;
exception when others then
        status:='Transaction Failure';
        trans_id:=null;
        rollback;  
end pay_to_merchant;

create or replace procedure refund_to_customer(
trans_id in number,
merch_id in varchar,
amount in number,
status out varchar
) as 
mob_no number;
confirmation number;
acc_no number;
iid number;
balance_amnt number;
begin
    select mobile_no into mob_no from merchant_details where transaction_id=trans_id;
    select account_num into acc_no from bussiness_login where merchant_id=merch_id and status=1;
    confirmation:=mob_fn(mob_no);
    select transaction_iid.nextval into iid from dual;
    if(confirmation=1) then
        select balance into balance_amnt from account_details where account_no=acc_no;
        if(balance_amnt>=amount) then
            update kyc set kyc_wallet = kyc_wallet+amount where mobile_no=mob_no;
            update account_details set balance=balance-amount where account_no=acc_no;
            insert into merchant_details(transaction_id,merchant_id,mobile_no,amount,comments) values (iid,merch_id,mob_no,amount,'REFUND');
            status:='Transaction Successfull';
            commit;
        else
            status:='Not sufficient balance-Transaction Failed';
        end if;
    else
        status:='Mobile number not valid-Transaction Failed';
    end if;
exception when others then
    status:='Transaction Failed';
    rollback;
end refund_to_customer;

create or replace function mob_fn (m_mob in number) 
return number as mob number:= 0;
refer number:=0;
Begin
   select mobile_no into refer from account_details where mobile_no=m_mob and account_status='Active';
   if (refer=0) then
   mob:=0;
   else 
   mob:=1;
  Return mob;
  end if;
End mob_Fn;

create or replace function mob_fn1(m_mob in number) 
return number as 
mob number;
refer number;
Begin
   select mobile_no into refer from account_details where mobile_no=m_mob;
   if (refer=m_mob) then
   mob:=1;
  end if;
  Return mob;
exception when others then
mob:= 0;
 Return mob;
End mob_fn1;

create or replace function status_chk(
r_mob in number
)return number as confirmation1 number;
confirmation2 number;
sts_chk2 varchar2(50);
final_chk number;
begin
select account_status into sts_chk2 from account_details where mobile_no=r_mob;
if ( sts_chk2='Active') then
confirmation2:=1;
end if;
if(confirmation2=1) then
final_chk:=1;
else 
final_chk:=0;
end if;
return final_chk; 
end status_chk;

create or replace function kyc_status_chk(
Mob_No In Number
)return number
as
verified_result number;
kyc_chk varchar2(1);
begin
    select kyc_status into kyc_chk from kyc where mobile_no=Mob_No;
    if(kyc_chk='Y') then
            verified_result:=1;
        else
            verified_result:=0;
        end if;
  return verified_result;
end kyc_status_chk;



