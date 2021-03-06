drop database BR;
/*create database BR DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use BR;   */
create database BR;
use BR;
/*用户表*/
create table USER (
  id  varchar(32) primary key,
  user_name  varchar(255),
  user_id varchar(20),  /*学生号或教师工号*/
  user_password varchar(50),
  first_login_time datetime,
  activity int(5),
  state char(1),
  user_birth  date,
  user_gender int /*0:male 1:female*/,
  user_teleph varchar(40) ,
  role tinyint,
  user_email  varchar(100)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 ;

/*群组表*/
create table `GROUP`(
  id varchar(32) primary key,
  group_name varchar(255),
  group_admin_id varchar(32), /*外键关联user表id*/
  group_users_count int(5),
  description varchar(255)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*资源表*/
create table RESOURCE(
  id varchar(32) primary key,
  resource_real_name varchar(255),
  upload_user_id varchar(32),   /*外键关联user表id*/
  upload_time datetime,
  description varchar(255),
  resource_type varchar(10),
  key_words varchar(255),
  resource_mark int(3),
  resource_size bigint,
  category varchar(255) /*0_0_0*/,
  download_times int(5),
  share_times int(5),
  collect_times int(5),
  resource_snapshot_path varchar(255) ,
  delete_flag int /*0:代表资源存在 1：代表资源删除*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/**/
create table RESOURCE_COURSE(
  resource_id varchar(32),
  course_id varchar(32),
  primary key(`resource_id`,`course_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*标签表*/
create table TAG(
  id varchar(32) primary key,
  tag_name varchar(255),
  reference_times int(4)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*主题表*/
create table TOPIC(
  id varchar(32) primary key,
  community_id varchar(32),/*外键关联group表id*/
  author_id varchar(32),/*参照user表id*/
  title varchar(100),
  content text,
  pub_date datetime,
  delete_flag tinyint default 1
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*回复主题表*/
create table SUB_TOPIC(
  id varchar(32) primary key,
  reply_topic_id varchar(32),
  content text,
  author_id varchar(32),
  pub_date datetime
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户收藏资源连接表*/
create table USER_COLLECT_RESOURCE(
  user_id varchar(32) ,  /*参照用户表id*/
  resource_id varchar(32), /*参照资源表id*/
  primary key (`user_id`,`resource_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户下载资源连接表*/
create table USER_DOWNLOAD_RESOURCE(
  user_id varchar(32) ,  /*参照用户表id*/
  resource_id varchar(32), /*参照资源表id*/
  primary key (`user_id`,`resource_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户分享资源连接表*/
create table USER_SHARE_RESOURCE(
  user_id varchar(32) ,  /*参照用户表id*/
  resource_id varchar(32), /*参照资源表id*/
  primary key (`user_id`,`resource_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*群组用户连接表*/
create table GROUP_USER(
  group_id varchar(32),
  user_id varchar(32),
  primary key(`group_id`,`user_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*群组资源推荐表 */
create table GROUP_RESOURCE(
  group_id varchar(32),
  resource_id varchar(32),
  share_user_id varchar(32) ,
  primary key(`group_id`,`resource_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*用户标签连接表*/
create table USER_TAG(
  user_id varchar(32),
  tag_id varchar(32),
  primary key(`user_id`,`tag_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*标签资源连接表*/
create table TAG_RESOURCE(
  tag_id varchar(32),
  resource_id varchar(32),
  primary key(`tag_id`,`resource_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*文件夹表*/
create table DIRECTORY(
  id varchar(32) primary key,
  dir_name varchar(255),
  user_id varchar(32)/*参照user表id*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*资源推荐表*/
create table RESOURCE_RECOMMEND(
  id varchar(32) primary key,
  resource_id varchar(32),
  user_id varchar(32),
  `type` int,/*0：系统自动推荐1：用户分享2：组分享*/
  rs_rmd_id varchar(32),  /*根据type字段，选择关联user或group表id，系统推荐则为0*/
  read_flag int /*标记用户是否已经读过*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户关系表*/
create table USER_RELATION(
  id varchar(32) primary key,
  user_id varchar(32),  /*外键关联usre表id*/
  friend_id varchar(32),  /*外键关联usre表id */
  follow_id varchar(32),   /*外键关联usre表id */
  friend_group_id varchar(32)  /*外键关联friend_group表id，没有分组为默认值0*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户分组表*/
create table FRIEND_GROUP(
  id  varchar(32) primary key,
  user_id varchar(32),
  friend_group_name varchar(255)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*用户行为表*/
create table USER_BEHAVIOR(
  id varchar(32) primary key,
  user_id varchar(32),
  `time` datetime,
  behavior_type int,/*0:用户分享资源行为1：用户收藏资源行为2：用户下载资源行为3：用户上传资源行为4：用户评论行为*/
  resource_id varchar(32),   /*外键关联resource表的id*/
  topic_id varchar(32)  /*外键关联topic表的id*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*各个学院常量表*/
create table ACADEMY(
  id varchar(32) primary key,
  name varchar(30) not null
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

create table MAJOR(
  id varchar(32) primary key,
  name varchar(30) not null,
  academy_id varchar(32)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

create table COURSE(
  id varchar(32) primary key,
  name varchar(30) not null,
  major_id varchar(32),
  term int/*1:一学期；2：二学期*。。。。。*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

create table USER_FOLLOW_MAJOR(
  user_id varchar(32),
  major_id varchar(32),
  primary key(`user_id`,`major_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;


create table USER_FOLLOW_COURSE(
  user_id varchar(32),
  course_id varchar(32),
  in_community int,
  invite int,
  community_id varchar(32);
  primary key(`user_id`,`course_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*社区表*/
create table COMMUNITY(
 id varchar(32) primary key,
 name varchar(255),
 admin_id varchar(32),     /*外键关联USER表老师id*/
 state tinyint,  /*0:激活状态，1：为激活状态*/
 course_id varchar(32)    /*社区对应的课程*/
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*社区,用户多对多关联表*/
create table COMMUNITY_USER(
 community_id varchar(32),
 user_id varchar(32),
 primary key(`community_id`,`user_id`)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*社区公告*/
create table BULLETIN(
  id varchar(32) primary key,
  community_id varchar(32),
  content varchar(255),
  pub_date timestamp
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
/*推荐书籍表*/
create table HOT_BOOK(
   id varchar(32) primary key,
   user_id varchar(32), /*外间关联user表id，如果是热门图书，该值为空值，如果是根据用户学号推荐图书，则该值为用户id */
   english_name varchar(200),
   chinese_name varchar(200),
   description text,
   author varchar(200),
   publisher varchar(200),
   isbn varchar(32),
   img_url varchar(100),
   douban_href varchar(100),
   lab_href varchar(100),
   price varchar(32),
   pages varchar(32),
   flag int,     /*0:代表图书馆排行榜抓取，1：代表根据图书馆热搜词，豆瓣抓去,3:根据用户id获取推荐图书*/
   pub_date varchar(15),
   douban_id varchar(10),
   binding varchar(15),
   tag varchar(255),
   rating varchar(10)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*豆瓣图书数据*/
create table DoubanJson(
  id varchar(32) primary key,
  title varchar(255),
  authors varchar(255),
  translators varchar(255),
  pub_date varchar(255),
  publisher varchar(255),
  price varchar(255),
  summary text,
  alt varchar(255),
  isbn10 varchar(255),
  smallPic varchar(255),
  mediumPic varchar(255),
  largePic varchar(255),
  url varchar(255),
  course_id varchar(32)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;

/*资源评论表*/
create table RESOURCE_COMMENT(
   id varchar(32) primary key,
   content text,
   resource_id varchar(32),
   pub_user_id varchar(32),
   pub_date datetime,
   mark int
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8;
