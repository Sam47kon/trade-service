import {OrderItem} from './order-item';
import DateTimeFormat = Intl.DateTimeFormat;

export class Order {
  orderId: number;
  clientName: string;
  date: DateTimeFormat;
  address: string;
  orderItems: OrderItem[];
}
