USE enterprise_helpdesk;

-- 1. Required table
SELECT CASE WHEN COUNT(*) = 1 THEN 'PASS: service_tickets exists'
            ELSE 'FAIL: service_tickets missing' END AS check_result
FROM information_schema.tables
WHERE table_schema = DATABASE() AND table_name = 'service_tickets';

-- 2. Required columns
SELECT column_name, data_type, is_nullable
FROM information_schema.columns
WHERE table_schema = DATABASE() AND table_name = 'service_tickets'
ORDER BY ordinal_position;

-- 3. Primary key
SELECT CASE WHEN COUNT(*) = 1 THEN 'PASS: primary key exists'
            ELSE 'FAIL: primary key missing' END AS check_result
FROM information_schema.table_constraints
WHERE table_schema = DATABASE()
  AND table_name = 'service_tickets'
  AND constraint_type = 'PRIMARY KEY';

-- 4. Nullability check
SELECT CASE WHEN COUNT(*) = 8 THEN 'PASS: required columns are NOT NULL'
            ELSE 'REVIEW: unexpected nullability' END AS check_result
FROM information_schema.columns
WHERE table_schema = DATABASE()
  AND table_name = 'service_tickets'
  AND is_nullable = 'NO';
