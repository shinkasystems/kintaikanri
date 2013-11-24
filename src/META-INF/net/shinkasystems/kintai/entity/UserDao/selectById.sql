select
  ID,
  AUTHORITY_ID,
  USER_NAME,
  PASSWORD,
  DISPLAY_NAME,
  ACTIVATED,
  EXPIRE_DATE,
  ROLE
from
  USER
where
  ID = /* id */1
