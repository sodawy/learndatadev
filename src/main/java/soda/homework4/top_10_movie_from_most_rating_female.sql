-- 困难：找出影评次数最多的女士所给出最高分的10部电影的平均影评分，展示电影名和平均影评分（可使用多行SQL）

WITH top10_movie_from_frist_lady AS (select RR.movieid as mid, RR.rate as rt
                                     from t_rating RR
                                              inner join
                                          (select t_user.userid as uid, sum(1) as cnt
                                           from t_rating
                                                    INNER JOIN t_user ON t_user.userid = t_rating.userid
                                           where t_user.sex = 'F'
                                           group by t_user.userid
                                           order by cnt desc
                                           LIMIT 1) R ON R.uid = RR.userid
                                     ORDER BY rt desc
                                     LIMIT 10)

SELECT M.moviename as `t.moviename`, avg(R.rate) as `t.avgrate`
FROM top10_movie_from_frist_lady T
         LEFT JOIN t_movie M ON T.mid = M.movieid
         LEFT JOIN t_rating R ON T.mid = R.movieid
GROUP BY M.moviename
ORDER BY `t.avgrate` desc