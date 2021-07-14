insert into user (id,active,fullname,role,username,password) values
    ('default_admin',true,'Administrator','ADMIN','admin','$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

insert into karyawan (id, fullname, email, phone,address, finger_print_id, gaji_pokok, uang_makan, uang_transport) values
    ('KARYAWAN_01','Iqbal', '11172804@nusamandiri.ac.id','08123123121','Jl. Taman Malaka','201607027',4000000.00,500000.00,500000.00),
    ('KARYAWAN_02','Aji', 'aji@yopmail.com','08123123122','Jl. Taman Malaka','20160928',4000000.00,500000.00,500000.00),
    ('KARYAWAN_03','Galang', 'galang@yopmail.com','08123123123','Jl. Taman Malaka','4444',4000000.00,500000.00,500000.00),
    ('KARYAWAN_04','Agung', 'agung@yopmail.com','08123123124','Jl. Taman Malaka','8996547',4000000.00,500000.00,500000.00),
    ('KARYAWAN_05','Dian', 'dian@yopmail.com','08123123125','Jl. Taman Malaka','5555',4000000.00,500000.00,500000.00),
    ('KARYAWAN_06','Ivans', 'ivans@yopmail.com','08123123126','Jl. Taman Malaka','201607032',4000000.00,500000.00,500000.00),
    ('KARYAWAN_07','Anggi', 'anggi@yopmail.com','08123123127','Jl. Taman Malaka','20120716',4000000.00,500000.00,500000.00),
    ('KARYAWAN_08','Dadanks', 'dadanks@yomail.com','08123123128','Jl. Taman Malaka','20081204',4000000.00,500000.00,500000.00),
    ('KARYAWAN_09','Wahyu', 'wahyu@yomail.com','08123123129','Jl. Taman Malaka','20160929',4000000.00,500000.00,500000.00),
    ('KARYAWAN_10','Indra Monoy', 'indra@yomail.com','08123123130','Jl. Taman Malaka','20130625',4000000.00,500000.00,500000.00);

insert into hari_libur (id, start_date, end_date, nama) values
    ('LIBUR_01','2021-05-01','2021-05-01','Hari Buruh Internasional'),
    ('LIBUR_02','2021-05-03','2021-05-21','Idul Fitri & WFH'),
    ('LIBUR_03','2021-05-13','2021-05-13','Hari Kenaikan Isa Al Masih'),
    ('LIBUR_04','2021-05-26','2021-05-26','Hari Raya Waisak'),
    ('LIBUR_05','2021-05-31','2021-06-01','Hari Kejepit & Pancasila'),
    ('LIBUR_06','2021-07-19','2021-07-20','Hari Idul Adha');

insert into cuti_karyawan (id, tanggal_cuti, keterangan_cuti, id_karyawan) values
    ('CUTI_01','2021-06-07','Acara keluarga','KARYAWAN_01');

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