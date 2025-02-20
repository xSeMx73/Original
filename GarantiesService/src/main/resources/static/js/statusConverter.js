
 function statusConverterToString(status) {
    if (status === "CREATED") {
        return "Создана"
    }
     if (status === "SENDPARTDEALER") {
         return "Запчасть отправлена поставщику"
     }
     if (status === "SENDREQUESTDEALER") {
         return "Заявка отправлена поставщику"
     }
     if (status === "APPROVED") {
         return "Одобрено"
     }
     if (status === "REJECTED") {
         return "Отклонено"
     }
     if (status === "REQUESTMOREINFO") {
         return "Запрос дополнительной информации поставщиком"
     }
 }