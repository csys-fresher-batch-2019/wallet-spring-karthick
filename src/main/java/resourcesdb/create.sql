create table account_details(
user_name varchar(100) not null,
mobile_no bigint(10),
email_id varchar(100) not null,
account_no bigint(10) not null,
balance float not null,
account_status varchar(15) default 'Active',
constraint user_pk unique(user_name),
constraint mob_uq primary key(mobile_no),
constraint mob_no_chk check( 6000000000<=mobile_no and mobile_no<=9999999999),
constraint email_uq unique(email_id),
constraint acc_no_pk unique(account_no),
constraint acc_no_chk check( 1000000000<=account_no and account_no<=9999999999)
);

create table login(
mobile_no bigint(10) not null,
upi_passwd int(4) not null,
status int default 1,
constraint upi_pass_chk check(1000<=upi_passwd and upi_passwd<=9999),
constraint mob_for foreign key(mobile_no) references account_details(mobile_no)
);


create table bussiness_login(
mobile_no bigint(10) not null,
merchant_id varchar(50) not null,
account_num bigint(10) not null,
status int default 1,
constraint mob_fk foreign key(mobile_no) references account_details(mobile_no),
constraint acc_num_pk unique(account_num),
constraint acc_num_chk check( 1000000000<=account_num and account_num<=9999999999)
);


create table transaction_table(
mobile_no bigint(10) not null,
rec_mob_no bigint(10) not null,
categories varchar(50),
transaction_time timestamp default sysdate,
transaction_amount int not null,
status varchar(50) default 'Success',
comments varchar(100),
constraint amount_ck check(transaction_amount>0),
constraint category_ck check(categories in ('Credited','Debited','Failed')),
constraint transaction_chk check(status in ('Success','Failure')),
constraint mobi_fk foreign key(mobile_no) references account_details(mobile_no)
);


create table kyc(
mobile_no bigint(10) not null,
aadhar_no varchar(12),
kyc_username varchar(50),
passport_no varchar(8),
driving_license_no varchar(15),
kyc_status varchar(1) default 'N',
kyc_wallet int,
constraint aadhar_no_uq unique(aadhar_no),
constraint passport_no_uq unique(passport_no),
constraint driving_no_uq unique(driving_license_no),
constraint mobile_for_k foreign key(mobile_no) references account_details(mobile_no),
constraint kyc_chk check (kyc_status in ('Y','N'))
);


create table failed_wallet_trans(
mobile_no bigint(10) not null,
rec_mob_no bigint(10) not null,
categories varchar(50) default 'Failed',
transaction_time timestamp default sysdate,
transaction_amount int not null,
transaction_status varchar(50) default 'Failure'
);


create table merchant_details(
transaction_id int primary key auto_increment,
merchant_id varchar(50) not null,
mobile_no bigint(10) not null,
amount float not null,
comments varchar(10) default 'PAID',
transaction_time timestamp default sysdate,
constraint mob_no_ck check( 6000000000<=mobile_no and mobile_no<=9999999999),
constraint mob_fr_k foreign key(mobile_no) references account_details(mobile_no),
constraint comments_ck check (comments in ('PAID','REFUND'))
);
