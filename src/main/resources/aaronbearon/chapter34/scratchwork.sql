SHOW DATABASES; # e.g. sys, javabook
USE javabook;

# Get all the tables!
# e.g. entrollment, college, course
SHOW TABLES;

# Get the schema (columns) for one table, method 1
# unsorted, can sort in java
DESCRIBE enrollment;

# Get the schema (columns) for one table, method 2
# a more complciated way to get the column names for a table already sorted.
SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'javabook' AND TABLE_NAME = 'enrollment'
ORDER BY COLUMN_NAME ASC;

# Get the specific columns you discovered, in sort order:
SELECT courseID, dateRegistered, grade, ssn from enrollment;

# Get all columns in database order:
SELECT * from faculty;

SELECT * FROM enrollment WHERE TABLE_SCHEMA = 'javabook';

SELECT * FROM information_schema.TABLES 
WHERE TABLE_SCHEMA = 'javabook' AND TABLE_NAME = 'enrollment';

SELECT * FROM college;