package ru.sem.orderbook.order.service.orderBuilder;

import org.springframework.stereotype.Service;

@Service
public class PriceBuilder {

  public static int buildPrice(String price) {
   int intPrice = (int) Double.parseDouble(price);
   if (intPrice < 300) {
      return (int) (intPrice + ((intPrice / 100.0) * 40));
   } else if (intPrice < 1000) {
       return (int) Math.abs((intPrice + ((intPrice / 100.0) * 27)) / 10) * 10;
   } else if (intPrice < 5000) {
       return (int) Math.abs((intPrice + ((intPrice / 100.0) * 22)) / 10) * 10;
   } else if (intPrice < 10000) {
       return (int) Math.abs((intPrice + ((intPrice / 100.0) * 17)) / 10) * 10;
   } else if (intPrice < 20000) {
       return (int) Math.abs((intPrice + ((intPrice / 100.0) * 15)) / 10) * 10;
   } else if (intPrice < 30000) {
       return (int) Math.abs((intPrice + ((intPrice / 100.0) * 11)) / 10) * 10;
   }
      return (int) Math.abs((intPrice + ((intPrice / 100.0) * 9)) / 10) * 10;
   }
}
