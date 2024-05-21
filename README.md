# RESTful Backend (server + database)

The **RESTful Backend** will collect the Raynauds attacks from participants and serve these data in the **web portal**. More details are shown below.

## Features

- **Save Profile of new participants**
  - participant id
  - username
  - corresponding device uuid (it's OK you design as one participant only use one phone)

- **Save Raynauds attack report records**
  - attack id
  - attack date
  - attack time
  - date and time should be saved as UTC/GMT (or in milliseconds, to easily be transformed into local when display)
  - location

- **Save Profile of Clinician to let them login in the Dashboard**
  - clinician id
  - username
  - password

## Technique

To implement **RESTful Backend**, you should use the following framework:

- Spring Boot + Spring MVC + Spring data JPA

To implement **Database**, you can choose one of the following databases:

- MySQL

## Screenshots of Database

![Example Image](https://github.com/Pengfei-Y/renaultBackend/blob/main/img/%E6%88%AA%E5%B1%8F2024-05-21%20%E4%B8%8B%E5%8D%883.24.55.png)

![Example Image](https://github.com/Pengfei-Y/renaultBackend/blob/main/img/%E6%88%AA%E5%B1%8F2024-05-21%20%E4%B8%8B%E5%8D%883.25.21.png)

![Example Image](https://github.com/Pengfei-Y/renaultBackend/blob/main/img/%E6%88%AA%E5%B1%8F2024-05-21%20%E4%B8%8B%E5%8D%883.25.43.png)


