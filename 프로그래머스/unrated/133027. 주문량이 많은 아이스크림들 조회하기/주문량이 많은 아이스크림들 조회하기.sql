-- 코드를 입력하세요
SELECT
    R.flavor
FROM(

SELECT 
j.flavor,
J.july_total +  F.half_total as total
FROM(
    
SELECT
    FLAVOR, SUM(TOTAL_ORDER) as july_total
FROM JULY
GROUP BY FLAVOR) as J

LEFT JOIN 

(SELECT 
    FLAVOR, SUM(TOTAL_ORDER) as half_total
FROM FIRST_HALF
GROUP BY FLAVOR) as F ON J.flavor = f.flavor
) R
ORDER BY total DESC LIMIT 3
