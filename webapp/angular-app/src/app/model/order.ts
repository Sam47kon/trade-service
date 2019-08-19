import {OrderItem} from './order-item';

export class Order {
  orderId: number;
  clientName: string;
  date: Date;
  address: string;
  orderItems: OrderItem[];
}
