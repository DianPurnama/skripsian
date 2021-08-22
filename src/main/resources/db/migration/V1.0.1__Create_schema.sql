create table cuti_karyawan (
    id varchar(255) not null,
    keterangan_cuti varchar(255),
    tanggal_cuti date not null,
    id_karyawan varchar(255),
    primary key (id)
) engine=InnoDB;

create table gaji_karyawan (
    id varchar(255) not null,
    gaji_pokok decimal(19,2) not null,
    uang_makan decimal(19,2) not null,
    uang_transport decimal(19,2) not null,
    id_karyawan varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table hari_libur (
    id varchar(255) not null,
    end_date date not null,
    nama varchar(255),
    start_date date not null,
    primary key (id)
) engine=InnoDB;

create table karyawan (
    id varchar(255) not null,
    address varchar(255),
    email varchar(255),
    finger_print_id varchar(255),
    fullname varchar(255),
    phone varchar(255),
    primary key (id)
) engine=InnoDB;

create table presensi (
    id varchar(255) not null,
    izin bit not null,
    tanggal date not null,
    waktu_absen time not null,
    izin_type varchar(255),
    id_karyawan varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table slip_gaji (
    id varchar(255) not null,
    bulan integer not null,
    created_time datetime(6) not null,
    denda_absent decimal(19,2) not null,
    denda_izin decimal(19,2) not null,
    denda_telat decimal(19,2) not null,
    tahun integer not null,
    total_denda decimal(19,2) not null,
    total_gaji decimal(19,2) not null,
    id_karyawan varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table user (
    id varchar(255) not null,
    active bit,
    fullname varchar(255) not null,
    password varchar(255) not null,
    role varchar(255) not null,
    username varchar(100) not null,
    primary key (id)
) engine=InnoDB;

alter table karyawan
    add constraint UK_69061kij6h7hqq522njt6a6wf unique (email);

alter table karyawan
    add constraint UK_qbnlexf7lrstsk8ftpe2ws19c unique (finger_print_id);

alter table karyawan
    add constraint UK_mqijiemqe57gx6p4a12lmw0qy unique (phone); create index idx_absen_karyawan on presensi (id_karyawan); create index idx_absen_tanggal on presensi (tanggal);

alter table presensi
    add constraint UKdla3sbq5rp6j2bnhm6j7txq2b unique (id_karyawan, tanggal);

alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

alter table cuti_karyawan
    add constraint FK7qbuib9df7ku5aah9yeavfb0u
    foreign key (id_karyawan)
    references karyawan (id);

alter table gaji_karyawan
    add constraint FKoeskrd1q90slw6kwvo5l3lrc8
    foreign key (id_karyawan)
    references karyawan (id);

alter table presensi
    add constraint FK1589do495ap48iglfm6grspo7
    foreign key (id_karyawan)
    references karyawan (id);

alter table slip_gaji
    add constraint FKbvmelsthv8kcy038ohcmgm63w
    foreign key (id_karyawan)
    references karyawan (id);
