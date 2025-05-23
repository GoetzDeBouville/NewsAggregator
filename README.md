# NewsAggergator

api - https://www.theguardian.com

### Stack:
- Coroutines + Flow
- Arcitecture: MVI+StateFlow, Single Activity + Android Navigation
- Network: Retrofit2
- Room DB
- Coil
- DI - Hilt
- Jetpack Navigation
- Jetpack Compose
- pull-to-refresh
- Multi-modules (поздно получил ответ от hr, поэтому решил оставить многомодульность)
- Responsive and adaptive layouts (for Medium and Expanded displays)

## Пункты по ТЗ
✅Новости берутся из rss канала https://www.theguardian.com/international/rss газеты Guardian https://www.theguardian.com

✅Приложение должно показывать новости вертикальным списком - можно воспользоваться LazyColumn.

✅По желанию и при наличии места можно также показать дату публикации, автора новости и тэги (элементы category). (кроме автора)

✅По щелчку на новость в списке она должна открываться в новом экране, например внутри WebView, используя url из элемента guid.

✅Задание выполнено на compose

✅Многопоточность можно реализовать или на Coroutines + Flow

✅Clean architecture + MVI

✅использовать библиотеки для внедрения зависимостей - Hilt

✅кэшировать загруженные новости в базе данных, чтобы приложение могло ограниченно работать оффлайн - реализован SSOT DB

✅всячески улучшить пользовательский опыт:
    - адаптирована верстка под fold телефоны (поддержка medium и expanded дисплеев), 
    - адаптация под пользовательские темы
    - экран состояний (пустой список, ошибки сервера/клиента, состояние загрузки и т.д.)

✅реализовать сортировку новостей, например по времени их публикации - новости сортируются по дате на стадии кэширования и отображаются в отсортированном виде

✅progress indicators

✅поиск новостей по тексту в заголовках/описании/тэгах – поиск по тегам

✅реализовать “Поделиться …”

✅обработку ошибок с оповещением пользователей о проблемах которые они могут самостоятельно устранить – при отсутствии – в случае ошибок появляется тост с текстом ошибки, но если есть закэшированные новости, то пользователь все равно увидит ленту


❌периодически подгружать в базу данных новости используя, например, WorkManager или Service

❌написать тесты для бизнес-логики

❌реализовать фильтрацию новостей через облако тэгов (элементы category)

Из улучшений можно заменить viewmodel на Store (использовать реализацию MVIKotlin), об этом я писал статью:
https://medium.com/@heoderer/refactoring-experience-transitioning-from-mvi-with-viewmodel-to-mvikotlin-e1e83482088f

## Screencast

<img src="./demo/screencast_00.gif" width="800" height="567"> 
<img src="./demo/screencast_01.gif" width="800" height="567"> 
