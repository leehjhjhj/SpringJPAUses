# 1주차 중요한 것
- 연관 관계 매핑
- Many to one 이나 One to one 같은 경우에는 지연 로딩이 되지 않으므로 fetch = FetchType.LAZY,을 적어야함 
- 같은 id라도 테이블 필드에서는 구분이 안되기 때문에 ~_id 를 달아준다.


# 1주차 모르는 것
### 연관 관계 매핑에 대하여
- 내가 이해한 것 -> fk로 엮을 때, 연관 관계의 주인은 many쪽, 즉 fk를 가지고 있는 쪽이 연관 관계의 주인이 되며, 연관 관계 주인이 @JoinColumn을 달아준다.
- 나머지 쪽은(fk가 없는 곳)은 거울일 뿐이며 mapped by를 써서 거울임을 알려줌.
- one to one 인 경우에 어디에 주인을 하느냐는 자유이나 구조를 잘 생각해서 주인을 지정
- 얘를 들어서 멤버와 주문? 주문에서 멤버 정보를 확인하기 때문에 주인을 order에 달고 member는 거울 역할
- 그리고 CASCADE 또한 주인에게 단다.
### 그 외
- @Embedded 이게 뭐지
- 연관관계 메서드 양방향일 때
  - 이건.. 왜하는 건가요?
  - 
    public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
    }
  - 
    public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this); // 여기서 this가 뭔가요?
    }
  - 
    public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
    delivery.setOrder(this);
    }