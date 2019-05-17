## <font color=#00dddd>Framework/DB</font>
> Maven Project
- Spring boot 2.1.4.RELEASE
- Spring boot starter web
- Spring boot starter test
- Spring Data JPA
- Hibernate
- H2
- Mockito
- Lombok
***

## <font color=#00dddd>Functions</font>

> 員工資料
- API URL
```sh
http://localhost:8080/empData/
```
- Request Header
```sh
Content-Type:application/json
```
  ##### <u>新增員工資料</u>
  - Http Method: ```POST ```
  - Endpoint: /empData/
  - Request Body : 
```sh
[
	{
	  "empName": "Leo",
	  "empDeptId": "IT",
	  "empGender": "M",
	  "empPhoneNo": "0912001002",
	  "empAddress": "TAIWAN",
	  "empAge": 32
	}
]
```
> 此處設計為可接受批次新增新進員工資料
> 單次多筆新增Transactional-rollbackOnException

  ##### <u>更新員工資料</u>
  - Http Method: ```PATCH ```
  - Endpoint: /empData/```{empNo}```
 - Request Body : 
```sh
{
  "empName": "Leona",
  "empDeptId": "IT",
  "empGender": "F",
  "empPhoneNo": "0912001002",
  "empAddress": "TAIWAN",
  "empAge": 32
}
```
  ##### <u>刪除員工資料</u>
  - Http Method: ```DELETE ```
  - Endpoint: /empData/```{empNo}```

  ##### <u>查詢員工資料</u>
  > 帶有動態查詢條件(**4項**)的**PostMapping**
  - Http Method: ```POST ```
  - Endpoint: /empData/```find/findEmpDataByPage```
  - Request Body : 
```sh
{
  "empName": "Zyire",
  "empNo": 1,
  "empAge": 32, 
  "empDeptName": "科技魔法部",
  "PageNumber": 0
}
```

### 員工部門
- API URL
```sh
http://localhost:8080/empDept/
```
- Request Header
```sh
Content-Type:application/json
```
  ##### <u>新增部門資料</u>
  - Http Method: ```POST ```
  - Endpoint: /empDept/
  - Request Body : 
```sh
{
  "empDeptId": "IQ",
  "empDeptName": "AI研發中心"
}
```

  ##### <u>更新部門資料</u>
  - Http Method: ```PATCH ```
  - Endpoint: /empDept/```{empDeptId}```
  - Request Body : 
```sh
{
  "empDeptName": "集團民生發展中心"
}
```
  ##### <u>刪除部門資料</u>
  - Http Method: ```DELETE ```
  - Endpoint: /empDept/```{empDeptId}```

***
## <font color=#00dddd>Table/Schema</font>
### Emp_Data_Master
| ColumnName | Type | NotNull | Comment |
|---|---|:-:|---|
| emp_no | IDENTITY |V| 員工編號 |
| emp_dept_id | VARCHAR(15) |V| 員工部門ID |
| emp_name | VARCHAR(50) |V| 員工姓名 |
| emp_gender | VARCHAR(10) || 員工性別-MALE:男性、FEMALE:女性、OTHER:其他 |
| emp_phone_no | VARCHAR(15) || 員工電話 |
| emp_address | VARCHAR(150) || 員工地址 |
| emp_age | INTEGER || 員工年齡 |
| create_time | TIMESTAMP || 建立時間 |
| update_time | TIMESTAMP || 最後修改時間 |

### Emp_Dept
| ColumnName | Type | NotNull | Comment |
|---|---|:-:|---|
| emp_dept_id | VARCHAR(15) |V| 員工部門ID |
| emp_dept_name | VARCHAR(50) |V| 員工部門名稱 |

#### 期初資料

員工期初資料 **15** 筆
部門期初資料 **3** 筆
> 請參閱 \src\main\resources\ **schema.sql**
***
## <font color=#00dddd>UnitTest</font>
> **Service調用測試  - 共14項**
### EmpDataServiceTest
  - 新增員工資料流程 : 正常(1)&異常(1) - 共**2**項
  - 更新員工資料流程 : 正常(1)&異常(2) - 共**3**項
  - 刪除員工資料流程 : 正常(1)&異常(1) - 共**2**項
  - 查詢員工資料使用分頁流程 : 正常(1) - 共**1**項

### EmpDeptServiceTest
  - 新增部門資料流程 : 正常(1)&異常(1) - 共**2**項
  - 更新部門資料流程 : 正常(1)&異常(1) - 共**2**項
  - 刪除部門資料流程 : 正常(1)&異常(1) - 共**2**項
