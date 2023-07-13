# Manage-diet

### 기능 상세
[칼로리 기준 레시피 추천]

* 레시피 데이터를 관리하고 있다 라고 가정하고, 레시피 데이터는 칼로리 데이터를 가지고 있다
* 목표 칼로리를 입력하여 요청하면 요청 칼로리 이하의 레시피가 응답된다
* 태그(한식, 디저트 등)을 이용해서 좀 더 세분화 된 레시피를 응답받을 수 있다.
* 칼로리가 낮은 순으로 상위 10개 데이터를 추천하여 응답 받는다.

[하루 식단 추천]
* 레시피 검색을 통해 아침 점심 저녁 메뉴를 선택한다
* 하나의 메뉴를 검색하여 선택하면 이후 메뉴는 칼로리가 (권장칼로리 - 선택한 칼로리) 범위에서 조회가 된다.
* 성인 1일 권장 칼로리는 남자 2700kcal 여자 2000kcal이다
* 서비스의 동작 방식은 
  1. 원하는 하루 칼로리 선택 (default는 성인 1일 권장 칼로리)
  2. 아침, 점심, 저녁 메뉴 선택
