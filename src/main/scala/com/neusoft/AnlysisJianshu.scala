package com.neusoft

import org.ansj.recognition.impl.StopRecognition
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.spark.SparkContext
import org.apache.spark.sql.{Row, SQLContext, SparkSession}

object AnlysisJianshu {
  val spark: SparkSession = SparkSession
    .builder()
    .master("local[4]")
    .appName("test")
    .config("spark.sql.shuffle.partitions", "5")
    .config("spark.testing.memory", "512000000")
    .getOrCreate()
  val sc: SparkContext = spark.sparkContext
  val sqlContext: SQLContext = spark.sqlContext


  def anlysisData(slug: String): java.util.List[Row] = {
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select * from user where slug = '$slug') as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }

  def dynamic(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select type,count(*),time from dynamic where userid = $userid group by type order by STR_TO_DATE(time,'%Y-%m-%d %H:%i:%s')) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }

  def countByMonth(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select SUBSTR(time FROM 1 FOR 7),count(*) from dynamic where userid = $userid group by STR_TO_DATE(time,'%Y-%m')) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }

  def countByDay(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select SUBSTR(time FROM 1 FOR 10),count(*) from dynamic where userid = $userid group by STR_TO_DATE(time,'%Y-%m-%d')) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByHour(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid group by SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeek(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select weekday(STR_TO_DATE(time,'%Y-%m-%d %H:%i:%s')) , count(*) from dynamic where userid = $userid group by weekday(STR_TO_DATE(time,'%Y-%m-%d %H:%i:%s'))) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }

  def countByMonthShareNote(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select SUBSTR(time FROM 6 FOR 2),count(*) from dynamic where userid = $userid and type = 'share_notes' group by SUBSTR(time FROM 6 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeekTimeShareNote(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid and type = 'share_notes' group by WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeekTimeLikeNotes(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid and type = 'like_notes' group by WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeekTimeLikeUsers(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid and type = 'like_users' group by WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeekTimeComment(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid and type = 'comment_notes' group by WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }
  def countByWeekTimeRewardNotes(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2),count(*) from dynamic where userid = $userid and type = 'reward_notes' group by WEEKDAY(STR_TO_DATE(time,'%Y-%m-%d')),SUBSTR(time FROM 12 FOR 2)) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select * from user").collectAsList()
    list
  }


  def cnsplitComment(userid:String):java.util.List[Row]={
    System.setProperty("HADOOP_USER_NAME", "root")
    sqlContext.udf.register("cnsplit", cnsplit _)
    val jdbcDF = sqlContext.read.format("jdbc").options(
      Map("url" -> "jdbc:mysql://localhost:3306/jianshu",
        "driver" -> "com.mysql.jdbc.Driver",
        "dbtable" -> s"(select count(1) as count,GROUP_CONCAT(context SEPARATOR ' ') as line from dynamic where userid = $userid and type = 'comment_notes' group by userid ) as dynamic",
        "user" -> "root",
        "password" -> "root")
    ).load()
    jdbcDF.createOrReplaceTempView("user")
    val list = sqlContext.sql("select count,cnsplit(line) from user ").collectAsList()
    list
  }



  def cnsplit(content: String): String = {
    val filter = new StopRecognition()
    filter.insertStopNatures("w") //过滤掉标点
    val splited = ToAnalysis.parse(content).recognition(filter).toStringWithOutNature(" ")
    splited
  }
}
