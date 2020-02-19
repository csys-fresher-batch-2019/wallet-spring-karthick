CREATE OR REPLACE TRIGGER transid_tr
BEFORE INSERT ON merchant_details
FOR EACH ROW
BEGIN
IF :new.transaction_id  IS NULL THEN 
SELECT transaction_iid.NEXTVAL 
INTO :new.transaction_id 
FROM dual;
END IF;
END;
