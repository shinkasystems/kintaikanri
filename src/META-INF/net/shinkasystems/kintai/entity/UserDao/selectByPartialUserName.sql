select
  *
from
  USER
where
  USER_NAME like /* @contain(userName) */'%X%' escape '$'
