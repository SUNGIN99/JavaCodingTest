-- 코드를 입력하세요


SELECT * FROM(
SELECT 
    B.book_id, A.author_name, DATE_FORMAT(B.published_date, '%Y-%m-%d') as p_date
FROM BOOK B
JOIN AUTHOR A ON A.author_id = B.author_id
WHERE category = '경제') R
ORDER BY R.p_date