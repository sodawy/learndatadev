-- hive作业1： 简单：展示电影ID为2116这部电影各年龄段的平均影评分

SELECT t_user.age, avg(t_rating.rate)
FROM t_rating
         LEFT JOIN t_user ON t_user.userid = t_rating.userid
WHERE t_rating.movieid = 2116
GROUP BY t_user.age
ORDER BY t_user.age