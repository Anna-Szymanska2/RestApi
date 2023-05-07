
    insert into user_details (id, birth_date, name)
values (10001, CURRENT_DATE(), 'Ranga');
    insert into user_details (id, name, birth_date)
values (10002, 'Ravi', CURRENT_DATE());
    insert into post(id,description,user_id)
    values(20001,'I want to learn AWS', 10001);

    insert into post(id,description,user_id)
    values(20002,'I want to learn DevOps', 10001);

    insert into post(id,description,user_id)
    values(20003,'I want to Get AWS Certified', 10002);

    insert into post(id,description,user_id)
    values(20004,'I want to learn Multi Cloud', 10002);