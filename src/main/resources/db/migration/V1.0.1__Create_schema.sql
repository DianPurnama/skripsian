create table cuti_karyawan (
       id varchar(255) not null,
       tanggal_cuti date not null,
       id_karyawan varchar(255),
       primary key (id)
) engine=InnoDB;

create table hari_libur (
        id varchar(255) not null,
        start_date date not null,
        end_date date not null,
        nama varchar(255),
        primary key (id)
) engine=InnoDB;

create table karyawan (
        id varchar(255) not null,
        email varchar(255),
        finger_print_id varchar(255),
        fullname varchar(255),
        gaji_pokok decimal(19,2) not null,
        phone varchar(255),
        address varchar(255),
        uang_makan decimal(19,2) not null,
        uang_transport decimal(19,2) not null,
        primary key (id)
) engine=InnoDB;

create table presensi (
        id varchar(255) not null,
        tanggal date not null,
        waktu_absen time not null,
        id_karyawan varchar(255) not null,
        izin bit,
        primary key (id)
) engine=InnoDB;

create table rekap_denda_bulanan (
        id varchar(255) not null,
        bulan integer not null,
        created_time datetime(6) not null,
        tahun integer not null,
        todal_denda decimal(19,2),
        id_karyawan varchar(255) not null,
        primary key (id)
) engine=InnoDB;

create table slip_gaji (
        id varchar(255) not null,
        bulan integer not null,
        created_time datetime(6) not null,
        tahun integer not null,
        total_gaji decimal(19,2) not null,
        id_karyawan varchar(255) not null,
        id_rekap_denda_bulanan varchar(255) not null,
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
    add constraint UK_mqijiemqe57gx6p4a12lmw0qy unique (phone);

create index idx_absen_karyawan on presensi (id_karyawan);
create index idx_absen_tanggal on presensi (tanggal);

alter table presensi
    add constraint UKdla3sbq5rp6j2bnhm6j7txq2b unique (id_karyawan, tanggal);

alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

alter table cuti_karyawan
    add constraint FK7qbuib9df7ku5aah9yeavfb0u foreign key (id_karyawan) references karyawan (id);

alter table presensi
    add constraint FK1589do495ap48iglfm6grspo7 foreign key (id_karyawan) references karyawan (id);

alter table rekap_denda_bulanan
    add constraint FK1hkdoppgjul2mgjwouc2queye foreign key (id_karyawan) references karyawan (id);

alter table slip_gaji
    add constraint FKbvmelsthv8kcy038ohcmgm63w foreign key (id_karyawan) references karyawan (id);

alter table slip_gaji
    add constraint FKdmhq83i0pht8btfur0ff4c5it foreign key (id_rekap_denda_bulanan) references rekap_denda_bulanan (id);
