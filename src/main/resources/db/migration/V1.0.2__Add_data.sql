insert into user (id,active,fullname,role,username,password) values
    ('default_admin',true,'Administrator','ADMIN','admin','$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

insert into karyawan (id, fullname, email, phone,address, finger_print_id, gaji_pokok, uang_makan, uang_transport) values
    ('KARYAWAN_01','Mochammad Iqbal Pandoyo Pandoyo', '11172804@nusamandiri.ac.id','08123123123','Jl. Taman Malaka','201607027',4000000.00,500000.00,500000.00);

insert into hari_libur (id, start_date, end_date, nama) values
    ('LIBUR_01','2021-07-08','2021-07-08','Hari Libur Test'),
    ('LIBUR_02','2021-07-19','2021-07-20','Hari Libur Idul Adha');
