insert into user (id,active,fullname,role,username,password) values
    ('default_admin',true,'Administrator','ADMIN','admin','$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

insert into karyawan (id, fullname, email, phone,address, finger_print_id, gaji_pokok, uang_makan, uang_transport) values
    ('KARYAWAN_01','Iqbal', '11172804@nusamandiri.ac.id','08123123121','Jl. Taman Malaka','201607027',4000000.00,500000.00,500000.00),
    ('KARYAWAN_02','Aji', 'aji@yopmail.com','08123123122','Jl. Taman Malaka','201607028',4000000.00,500000.00,500000.00),
    ('KARYAWAN_03','Galang', 'galang@yopmail.com','08123123123','Jl. Taman Malaka','201607029',4000000.00,500000.00,500000.00),
    ('KARYAWAN_04','Agung', 'agung@yopmail.com','08123123124','Jl. Taman Malaka','201607030',4000000.00,500000.00,500000.00),
    ('KARYAWAN_05','Dian', 'dian@yopmail.com','08123123125','Jl. Taman Malaka','201607031',4000000.00,500000.00,500000.00),
    ('KARYAWAN_06','Ivans', 'ivans@yopmail.com','08123123126','Jl. Taman Malaka','201607032',4000000.00,500000.00,500000.00),
    ('KARYAWAN_07','Anggi', 'anggi@yopmail.com','08123123127','Jl. Taman Malaka','201607033',4000000.00,500000.00,500000.00),
    ('KARYAWAN_08','Dadanks', 'dadanks@yomail.com','08123123128','Jl. Taman Malaka','201607034',4000000.00,500000.00,500000.00);

insert into hari_libur (id, start_date, end_date, nama) values
    ('LIBUR_01','2021-07-08','2021-07-08','Hari Libur Test'),
    ('LIBUR_02','2021-07-19','2021-07-20','Hari Libur Idul Adha');

insert into presensi (id, tanggal, waktu_absen, id_karyawan, izin) values
    ('PRESENSI_01','2021-07-12','07:30:20','KARYAWAN_01',false),
    ('PRESENSI_02','2021-07-13','08:10:11','KARYAWAN_01',false),
    ('PRESENSI_03','2021-07-12','07:30:20','KARYAWAN_02',false),
    ('PRESENSI_04','2021-07-13','08:10:11','KARYAWAN_02',true),
    ('PRESENSI_05','2021-07-12','07:30:20','KARYAWAN_03',false),
    ('PRESENSI_06','2021-07-13','08:10:11','KARYAWAN_03',false),
    ('PRESENSI_07','2021-07-12','07:30:20','KARYAWAN_04',false),
    ('PRESENSI_08','2021-07-13','08:10:11','KARYAWAN_04',false),
    ('PRESENSI_09','2021-07-12','07:30:20','KARYAWAN_05',false),
    ('PRESENSI_10','2021-07-13','08:10:11','KARYAWAN_05',false),
    ('PRESENSI_11','2021-07-12','07:30:20','KARYAWAN_06',false),
    ('PRESENSI_12','2021-07-13','08:10:11','KARYAWAN_06',true),
    ('PRESENSI_13','2021-07-12','07:30:20','KARYAWAN_07',false),
    ('PRESENSI_14','2021-07-13','08:10:11','KARYAWAN_07',false),
    ('PRESENSI_15','2021-07-12','07:30:20','KARYAWAN_08',false),
    ('PRESENSI_16','2021-07-13','08:10:11','KARYAWAN_08',false);