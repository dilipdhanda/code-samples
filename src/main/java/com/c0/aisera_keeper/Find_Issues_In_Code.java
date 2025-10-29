package com.c0.aisera_keeper;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/* Keeper - 10/27 2pm Gene Dias
- N+1 queries - You fetch order IDs, then for each ID you hit the DB again for line items. This explodes to 1 + N queries. Use a single JOIN (or at least one IN (...)) and assemble in memory.
- SQL Injection, he did not think as issue, bc of ? And inside a function. Its issue when customers enter params.
- LinkedList no need, use ArrayList (I said)
- I said, create separate functions to get list and create order, even to get the ids, so high level logic in simple
- Immutable classes - asked and hinted they do not support setters like order.setId(id);
 */
public class Find_Issues_In_Code {
  public List<Order> getOrdersByDate(Date date) {
    List<Integer> orderIds = queryList(Integer.class, "select id from orders where order date between ? and ? + interval 1 day", date, date);
    List<Order> orders = new LinkedList<Order>();
    for (int id : orderIds) {
      List<OrderLineItem> items = queryList(OrderLineItem.class, "select * from order_line. _item where id=?", id);
      Order order = new Order();
      order.setId(id);
      order.setLineItems(items);
      orders.add(order);
    }
    return orders;
  }

  // Dummy implementations to make the code compile
  private class Order {
    public void setId(int id) { }
    public void setLineItems(List<OrderLineItem> items) { }
  }
  private class OrderLineItem { }
  private List<Integer> queryList(Class<Integer> integerClass, String s, Date date, Date date1) {
    return List.of();
  }
  private List<OrderLineItem> queryList(Class<OrderLineItem> orderLineItemClass, String s, int id) {
    return List.of();
  }
}
