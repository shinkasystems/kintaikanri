select
  count(*)
from
  INFORMATION_SCHEMA.TABLES
where
  TABLE_TYPE = 'TABLE'
