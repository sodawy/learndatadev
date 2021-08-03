-- 中等：找出男性评分最高且评分次数超过50次的10部电影，展示电影名，平均影评分和评分次数

SELECT t_user.sex           AS sex,
       t_movie.moviename    AS name,
       AVG(t_rating.rate)   AS avgrate,
       COUNT(t_rating.rate) AS total
FROM (
         SELECT t_rating.movieid   AS top_movieid,
                avg(t_rating.rate) AS avg_score
         FROM t_rating
                  LEFT JOIN t_user
                            ON t_user.userid = t_rating.userid
         WHERE t_user.sex = 'M'
         GROUP BY t_rating.movieid
         HAVING count(t_rating.rate) > 50
         ORDER BY avg_score desc
             LIMIT 10) top_movie
         LEFT JOIN t_rating ON t_rating.movieid = top_movie.top_movieid
         LEFT JOIN t_user ON t_user.userid = t_rating.userid
         LEFT JOIN t_movie ON t_movie.movieid = top_movie.top_movieid
WHERE t_user.sex = 'M'
GROUP BY t_user.sex, t_movie.moviename
ORDER BY avgrate DESC