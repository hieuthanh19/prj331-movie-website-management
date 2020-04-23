use WebsiteManager
SELECT TOP 5 * FROM Movie
ORDER BY m_views desc
delete from Director where d_id = 2
delete from Client where c_id = 10
delete from Account where acc_id = 10
select * from Movie where m_name like 'a'
select * from Client /*where acc_id = 1 */
select * from Account
select * from Studio/* where s_name like 'Marvel studios' */
insert into Account (acc_username, acc_password, acc_level, acc_status) values ('mod', 'mod', 1, 1)
insert into Client (c_name, c_email, c_gender, acc_id) values ('Admin', 'admin@admin.com', 1, 1)
alter table Movie
add m_duration int check (m_duration>0)
alter table Movie
	alter column m_name nvarchar(50) not null
alter table Actor 
	alter column a_name nvarchar(30) not null
alter table Director
	alter column d_name nvarchar(30) not null
alter table Studio 
	alter column s_name nvarchar(30) not null
alter table Country 
	alter column country_name nvarchar(30) not null
alter table Client 
	alter column c_name nvarchar(30) not null
alter table Actor 
	alter column a_name nvarchar(30) not null