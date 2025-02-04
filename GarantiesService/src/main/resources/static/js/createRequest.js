function createRequest() {
    closeModal()
    const modal = document.createElement('div');
    modal.id = 'modal';
    modal.className = 'modal';
    modal.innerHTML = `
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Форма заявки</h2>
            <form id="requestForm">
                <label for="clientName">Наименование клиента:</label>
                <input type="text" id="clientName" name="clientName" required>

                <label for="clientPhone">Номер телефона клиента:</label>
                <input type="tel" id="clientPhone" name="clientPhone" required>

                <label for="transportModel">Модель ТС:</label>
                <input type="text" id="transportModel" name="transportModel" required>

                <label for="transportBrand">Производитель ТС:</label>
                <input type="text" id="transportBrand" name="transportBrand" required>

                <label for="transportYear">Год выпуска ТС:</label>
                <input type="number" id="transportYear" name="transportYear" required>

                <label for="gosNumber">Гос номер ТС:</label>
                <input type="text" id="gosNumber" name="gosNumber" required>

                <label for="mileageStart">Пробег при установке:</label>
                <input type="number" id="mileageStart" name="mileageStart" required>

                <label for="mileageEnd">Пробег при снятии:</label>
                <input type="number" id="mileageEnd" name="mileageEnd" required>

                <label for="vin">VIN номер:</label>
                <input type="text" id="vin" name="vin" required>

                <label for="dateStartRepair">Дата установки запчасти:</label>
                <input type="date" id="dateStartRepair" name="dateStartRepair" required>

                <label for="dateRemovePart">Дата демонтажа запчасти:</label>
                <input type="date" id="dateRemovePart" name="dateRemovePart" required>

                <label for="partArticle">Артикул запчасти:</label>
                <input type="text" id="partArticle" name="partArticle" required>

                <label for="partBrand">Производитель запчасти:</label>
                <input type="text" id="partBrand" name="partBrand" required>

                <label for="partName">Наименование запчасти:</label>
                <input type="text" id="partName" name="partName" required>

                <label for="partDealer">Поставщик запчасти:</label>
                <input type="text" id="partDealer" name="partDealer" required>

                <label for="faultDescription">Описание неисправности:</label>
                <textarea id="faultDescription" name="faultDescription" required></textarea>

                <label for="createManager">Ответственный менеджер:</label>
                <input type="text" id="createManager" name="createManager" required>

                <button type="submit">Отправить заявку</button>
            </form>
        </div>
    `;
    document.body.appendChild(modal);
    modal.style.display = "block"; // Показываем модальное окно

    // Добавляем обработчик события submit для формы
    const form = document.getElementById('requestForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Предотвращаем стандартное поведение формы

        // Создаем объект с данными формы
        const formData = new FormData(form);
        const data = {};

        // Итерация по элементам FormData и добавление их в объект
        formData.forEach((value, key) => {
            data[key] = value;
        });

        // Отправляем данные на сервер
        fetch("http://192.168.1.135:9090/garanties", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data) // Преобразуем объект в JSON
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Ошибка сети при отправке данных");
                }
                return response.json(); // Если успешный ответ, парсим JSON
            })
            .then(data => {
                console.log("Успешный ответ с сервера:", data);
                closeModal(); // Закрываем модальное окно при успехе
            })
            .catch(error => {
                console.error("Ошибка при отправке данных:", error);
            });
    });
}

function closeModal() {
    const modal = document.getElementById('modal');
    if (modal) {
        modal.style.display = "none"; // Скрываем модальное окно
        document.body.removeChild(modal); // Удаляем модальное окно из DOM
    }
}