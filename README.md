# taskandtodolist

- Firstly, run dataInit.sql
- A generated user, user1 has the same password (1234)
- Port to run is 8087
- User for basic authentication is admin/admin, required only when you try to access /admin url

LIMITATION:
Currently, we only can search TODO by one of todoName/todoStatus/TodoDeadline/taskName.
Because I chose spring-data-jpa, so that, I cannot find the way to write native SQL for now.
By the way, I try to fix this issue ASAP.
Thanks