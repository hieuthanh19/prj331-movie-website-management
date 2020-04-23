drop database WebsiteManager
use WebsiteManager

create table Account (
 acc_id int identity (1,1) primary key,
 acc_username nvarchar(30) not null,
 acc_password nvarchar(30) not null,
 acc_level int check (acc_level>=0 and acc_level<=2) not null,
 acc_status nvarchar(10) not null
);
create table Client (
 c_id int identity (1,1) primary key,
 c_name nvarchar(20) not null,
 c_email nvarchar(30) not null,
 c_gender int check ((c_gender=0) or (c_gender = 1)) not null,
 acc_id int references Account (acc_id) not null,

);
create table Studio (
 s_id int identity (1,1) primary key,
 s_name nvarchar(20) not null
);

create table Country(
 country_id int identity (1,1) primary key,
 country_name nvarchar(20) not null, 
);

create table Movie (
 m_id int identity (1,1) primary key,
 m_name nvarchar(40) not null,
 m_year int check (m_year>=1888) not null,
 m_views int check (m_views >=0)  not null,
 m_trailer nvarchar(100) not null,
 m_thumbnail nvarchar(50) not null,
 s_id int references Studio(s_id) not null,
 country_id int references Country(country_id) not null
);

create table FavoriteMovie (
 c_id int references Client(c_id)  not null,
 m_id int references Movie(m_id) not null,
 primary key (c_id, m_id)
);

create table Director(
 d_id int identity (1,1) primary key,
 d_name nvarchar(20) not null,
 d_age int check (d_age>0) not null
);

create table Actor (
 a_id int identity (1,1) primary key,
 a_name nvarchar(20) not null,
 a_age int check (a_age>0) not null
);

create table Genre(
 g_id int identity (1,1) primary key,
 g_name nvarchar(20) not null,
);
create table DirectorInMovie(
 m_id int references Movie(m_id)  not null,
 d_id int references Director(d_id) not null,
 primary key (d_id, m_id)
);
create table ActorInMovie(
 a_id int references Actor(a_id)  not null,
 m_id int references Movie(m_id) not null,
 primary key (a_id, m_id)
);
create table GenreInMovir(
 g_id int references Genre(g_id)  not null,
 m_id int references Movie(m_id) not null,
 primary key (g_id, m_id)
);
