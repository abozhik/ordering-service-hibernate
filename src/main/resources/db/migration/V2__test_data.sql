create function insert_test_data()
    returns void
    language plpgsql
as
$$
DECLARE
    ordering_id_t        BIGINT;
BEGIN
    INSERT INTO ordering (user_name) VALUES ('da3548b2-b93c-4360-b0ce-4b61227c2dfb');
    INSERT INTO ordering (user_name) VALUES ('f6b04742-395f-4c0c-b83b-bacf845f2982');
    INSERT INTO ordering (user_name) VALUES ('e6bc007e-2236-4043-8664-e54bd67f6da3');
    INSERT INTO ordering (user_name) VALUES ('4dbce9dc-afaf-4e96-aad2-45d01e58781a');
    INSERT INTO ordering (user_name) VALUES ('497ffba1-5a22-46d2-9422-1d0b3d8c7653');
    INSERT INTO ordering (user_name) VALUES ('e92988c6-b5a7-4269-a94a-e868eff8715f');
    INSERT INTO ordering (user_name) VALUES ('70d8b377-aac8-42c2-855f-8ea055d0493b');
    INSERT INTO ordering (user_name) VALUES ('0272853c-83de-4edc-9a40-f2fdeafb2690');
    INSERT INTO ordering (user_name) VALUES ('f35f8597-7e91-470b-99ea-8d5460a39084');
    INSERT INTO ordering (user_name) VALUES ('142a2a23-10a7-4210-a69d-4752388ee45f');
    INSERT INTO ordering (user_name) VALUES ('1e993532-a3bd-4691-a159-f0359b846a41');
    INSERT INTO ordering (user_name) VALUES ('38dec7ec-a997-4741-bfcf-c26acb60297b');
    INSERT INTO ordering (user_name) VALUES ('ef3e474b-4025-490b-94a9-a35e72a1f4cb');
    INSERT INTO ordering (user_name) VALUES ('5104a43d-ee95-437d-919f-3f5a99840ad7');
    INSERT INTO ordering (user_name) VALUES ('797937bb-fe3e-410c-9e40-d6d7c1649425');
    INSERT INTO ordering (user_name) VALUES ('80ae1fe8-b992-4053-85a3-48c9bf11fb05');
    INSERT INTO ordering (user_name) VALUES ('c34899c0-8ff0-4903-bf2f-129bba28265a');
    INSERT INTO ordering (user_name) VALUES ('dc02c887-f656-423c-bb53-e9c9621cc995');
    INSERT INTO ordering (user_name) VALUES ('429d84f0-3030-47a9-9972-3223d03811bf');
    INSERT INTO ordering (user_name) VALUES ('6d7e2473-5f26-4272-afba-40aa6daa5ed6');
    INSERT INTO ordering (user_name) VALUES ('e6567d31-6b62-463b-b58f-e0bafc6a6859');
    INSERT INTO ordering (user_name) VALUES ('e5f9af46-3269-4ed2-8f33-e6ec92243440');
    INSERT INTO ordering (user_name) VALUES ('a5d24e98-1cac-42d7-9070-42f5034cde04');
    INSERT INTO ordering (user_name) VALUES ('315be338-44e9-47f6-943c-b9afd87ef71c');
    INSERT INTO ordering (user_name) VALUES ('3bdbf80f-eba2-4e8e-b219-4fccf9a90c21');
    INSERT INTO ordering (user_name) VALUES ('8f73eb3b-9458-4dd1-be78-ec630a8ce9de');
    INSERT INTO ordering (user_name) VALUES ('886ddd88-ff86-42bc-bd67-abc53cc32f4d');
    INSERT INTO ordering (user_name) VALUES ('1aa8eaaa-d1c8-40dc-af26-4fb0e0d7214d');
    INSERT INTO ordering (user_name) VALUES ('77fef66e-8035-470f-9c0c-9241de8b3f4e');
    INSERT INTO ordering (user_name) VALUES ('39f1d6c4-9154-45e0-aedb-383d720b351c');
    INSERT INTO ordering (user_name) VALUES ('7dad5f50-31c7-444b-b2d3-18314de5782c');
    INSERT INTO ordering (user_name) VALUES ('78666eb5-03ad-4a19-a1f7-eaa72856a96a');
    INSERT INTO ordering (user_name) VALUES ('be581c4b-1b3c-448c-b72f-538a380ea669');
    INSERT INTO ordering (user_name) VALUES ('dccbad13-1c9d-432c-8f80-d7055624a200');
    INSERT INTO ordering (user_name) VALUES ('5d6ca2ed-662f-4b81-b457-d65df13f577c');
    INSERT INTO ordering (user_name) VALUES ('895f6709-f023-4644-b989-c5e0bc069b3b');
    INSERT INTO ordering (user_name) VALUES ('51b26ac7-896b-42c9-824b-d51dc097995f');
    INSERT INTO ordering (user_name) VALUES ('559f77f3-fb5c-4ff2-9f77-d2a7f31b120d');
    INSERT INTO ordering (user_name) VALUES ('b51b2e81-0bb0-4569-9d29-e24b9536c2af');
    INSERT INTO ordering (user_name) VALUES ('0cf051fd-0926-423a-a40c-394db144e933');
    INSERT INTO ordering (user_name) VALUES ('61ea0bc1-fc54-4e2e-a8df-c41767b5fd14');
    INSERT INTO ordering (user_name) VALUES ('d8187c42-0bf4-4edf-bcf2-0d6da00b9185');
    INSERT INTO ordering (user_name) VALUES ('29088652-77d1-4bf5-8452-d7cd3f06ba5d');
    INSERT INTO ordering (user_name) VALUES ('e98c8768-5b67-4f0c-9374-b0aeb2e13419');
    INSERT INTO ordering (user_name) VALUES ('e7118898-ed80-45ab-b59d-e2b403e10034');
    INSERT INTO ordering (user_name) VALUES ('bf741385-a209-4aea-8dbc-d377b54e4f77');
    INSERT INTO ordering (user_name) VALUES ('54b19bc6-1987-4f5d-946c-251065261d13');
    INSERT INTO ordering (user_name) VALUES ('974a2139-361e-4e21-a0ab-69f1784aa204');
    INSERT INTO ordering (user_name) VALUES ('77cf86d8-1f46-4d01-a527-d7b64f6d86bb');
    INSERT INTO ordering (user_name) VALUES ('e44b5650-5a9d-4674-bf4d-5f7b1135e27b');
    INSERT INTO ordering (user_name) VALUES ('27725be4-1036-4697-b324-9c607ab24c18');
    INSERT INTO ordering (user_name) VALUES ('98448580-951a-4945-94bc-d6af673c2a43');
    INSERT INTO ordering (user_name) VALUES ('e77ba3e5-fcf9-4ad2-997f-fc4e37bdf097');
    INSERT INTO ordering (user_name) VALUES ('6c9b59ea-7f1d-4f31-8b51-dd0dac049202');
    INSERT INTO ordering (user_name) VALUES ('8ade47e0-dd15-4212-9112-79328b5a61fb');
    INSERT INTO ordering (user_name) VALUES ('9af12d9b-46d0-4d77-9da1-1206bb8c6d23');
    INSERT INTO ordering (user_name) VALUES ('23a07223-4674-480e-9f06-58c2ac4f39ad');
    INSERT INTO ordering (user_name) VALUES ('83c76fa6-7db7-4e65-8a78-e3163f776a20');
    INSERT INTO ordering (user_name) VALUES ('6a21a60d-3cc2-4448-b600-354ebf2f33ce');
    INSERT INTO ordering (user_name) VALUES ('513ee010-4de5-4b3c-a69f-7bd3632a720a');
    INSERT INTO ordering (user_name) VALUES ('540e5b29-d969-4e56-8462-63cb1ea8607a');
    INSERT INTO ordering (user_name) VALUES ('3b13e66e-8373-40a0-af7e-a0be7b9bc82f');
    INSERT INTO ordering (user_name) VALUES ('a43e4cc4-2a55-48bc-b495-ddfba87b02a7');
    INSERT INTO ordering (user_name) VALUES ('d8edc9ab-0345-4791-9405-c3baafa2cfb8');
    INSERT INTO ordering (user_name) VALUES ('4b20c7d4-4123-4900-b320-12dde33e7c85');
    INSERT INTO ordering (user_name) VALUES ('983b51f3-fc33-4ef9-9c62-ab8aa001bf8e');
    INSERT INTO ordering (user_name) VALUES ('526ad1e6-44da-43d2-b700-ac0019dc0407');
    INSERT INTO ordering (user_name) VALUES ('cda45396-283b-4220-ac4e-db85f99b2c0e');
    INSERT INTO ordering (user_name) VALUES ('47754a0d-33af-4e27-ab00-be66b4b9c5f5');
    INSERT INTO ordering (user_name) VALUES ('1e2d2d86-fd45-40b1-bf9d-d61a152e1d64');
    INSERT INTO ordering (user_name) VALUES ('6f862d64-2b00-453d-a3ae-2c1c375f3887');
    INSERT INTO ordering (user_name) VALUES ('6ae3c708-3899-4f61-920e-958d4aaaf260');
    INSERT INTO ordering (user_name) VALUES ('a8f2707c-62b4-4d54-ae09-f92e5993af4a');
    INSERT INTO ordering (user_name) VALUES ('679ba8f2-11b6-4892-96f4-b5fa23a9c84e');
    INSERT INTO ordering (user_name) VALUES ('44dcdf26-198c-41fb-b3f4-e37fd55c2b2d');
    INSERT INTO ordering (user_name) VALUES ('486ca4ca-3f4d-4e90-8a5a-dd5deb5b16bd');
    INSERT INTO ordering (user_name) VALUES ('8fc3a40f-93dd-42b5-8aad-e2797ad5e655');
    INSERT INTO ordering (user_name) VALUES ('bebda2fc-b95d-4785-946d-4b8b9821c493');
    INSERT INTO ordering (user_name) VALUES ('cdf4fd03-373b-4650-bc1e-f9126f01c34e');
    INSERT INTO ordering (user_name) VALUES ('ef7c5d74-68d0-41b3-9407-f908c1a1fdc8');
    INSERT INTO ordering (user_name) VALUES ('77f5bb55-32be-4a81-94a6-2f7b2940ccc1');
    INSERT INTO ordering (user_name) VALUES ('fc4924b8-6692-41e8-9b28-46ed6aaa0577');
    INSERT INTO ordering (user_name) VALUES ('0422f818-a6cf-482a-8691-4c9d39586921');
    INSERT INTO ordering (user_name) VALUES ('b774c081-cd01-482c-a003-2a4cdfb54e90');
    INSERT INTO ordering (user_name) VALUES ('c743bea4-ee6e-4009-8983-7dbda0dca31e');
    INSERT INTO ordering (user_name) VALUES ('9f4fbc30-88fa-450f-aed3-e0f7852dbe47');
    INSERT INTO ordering (user_name) VALUES ('8ee7a86b-7ee5-43a6-8caa-d5c8edcb7a41');
    INSERT INTO ordering (user_name) VALUES ('4d86dbbb-eb49-4894-af6e-afb1ee718042');
    INSERT INTO ordering (user_name) VALUES ('bfb6bda9-642e-4d3d-bdfa-c853afea825e');
    INSERT INTO ordering (user_name) VALUES ('c4c3c76b-ea92-47a9-9d19-d5881631cdda');
    INSERT INTO ordering (user_name) VALUES ('1acda22c-db38-4e82-b0b8-a615a00399e5');
    INSERT INTO ordering (user_name) VALUES ('56e6f9c3-a7b1-4783-a35a-2943a3743b49');
    INSERT INTO ordering (user_name) VALUES ('2919b4a7-5c1a-42ca-aed7-63d67cd0cbfc');
    INSERT INTO ordering (user_name) VALUES ('2565687a-42fb-4725-b049-5c12f98dab2f');
    INSERT INTO ordering (user_name) VALUES ('2b6972be-6c35-42dd-8ba5-56fe9ce90252');
    INSERT INTO ordering (user_name) VALUES ('0b936c20-73b0-4e2b-96d8-9845950323f6');
    INSERT INTO ordering (user_name) VALUES ('d15a1682-b9f2-4db2-ba32-d593832e9042');
    INSERT INTO ordering (user_name) VALUES ('0c40f1be-c55b-4e97-af4b-8f00da5d083c');
    INSERT INTO ordering (user_name) VALUES ('4f0e31fe-0276-44c3-af16-5e9ce15a626e') returning id into ordering_id_t;

    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '3ccd6474-16b1-47d9-90aa-8908ae177dad', 923, 749.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, 'ba329810-fc42-4085-9fa1-328e390aa287', 583, 127.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '7c7368c3-18c4-4e72-8a64-333c7b0ea765', 553, 941.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '2439f972-6064-4114-8f6c-ae1f8c1e0ddc', 707, 222.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '734b9932-702b-48de-852d-d653e43eb9c5', 662, 244.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '3055f7a5-263f-4435-b9fb-0292a493b6a0', 387, 422.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, '46e8c1b5-7a8a-42f0-80f0-4acf7b05d50b', 264, 538.00);
    INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) VALUES (1, 'bd1fdd02-1c19-4c09-aafc-7269b858ddc0', 321, 571.00);

END;
$$;

select insert_test_data();

drop function insert_test_data();


