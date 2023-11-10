import java.util.ArrayList;
import java.util.List;

class Article {

    final String getArticle;

    Article(final String getArticle) {
        this.getArticle = getArticle;
    }
}


// создайте интерфейс Subscriber

interface Subscriber{
    // интерфейс должен иметь всего один метод `void send()`, который принимает на вход объект класса `Article`
    void send(final Article article);

}



class OfflineSubscriber implements Subscriber /* класс должен реализовывать интерфейс Subscriber */ {

    private final String address;

    public OfflineSubscriber(final String address) {
        this.address = address;
    }

    // метод send интерфейса Subscriber должен выводить текст "{article.getArticle} была доставлена по адресу: {address}"
    public void send(final Article article){
        System.out.println(article.getArticle + " была доставлена по адресу: " + address);
    }

}

class WebSite implements Subscriber/* класс должен реализовывать интерфейс Subscriber */ {

    private final String url;

    public WebSite(final String url) {
        this.url = url;
    }

    // метод send интерфейса Subscriber должен выводить текст "{article.getArticle} опубликована на страничке: {address}"
    public void send(final Article article){
        System.out.println(article.getArticle + " опубликована на страничке: " + url);
    }
}

class NewspaperPublisher {
    private final List<Article> articles;

    // для отправки статей подписчикам, вам необходимо хранить их в списке
    // создайте пустой список подписчиков List<Subscriber>

    private final List<Subscriber> subscribers;

    public NewspaperPublisher(final List<Article> articles) {
        this.articles = articles;
        this.subscribers = new ArrayList<>();
    }

    // Создайте метод subscribe() принимающий на вход любой объект, реализующий интерфейс Subscriber.
    public void subscribe(final Subscriber subscriber){
        // В списке подписчиков не должно быть дубликатов! Вы можете проеверить, есть ли данный подписчик в списке методом List.contains().
        if (!subscribers.contains(subscriber))
            // При вызове метода subscribe() новый подписчик должен добавляться в список подписчиков.
            subscribers.add(subscriber);
    }

    // Создайте метод unsubscribe() принимающий на вход любой объект, реализующий интерфейс Subscriber.
    public void unsubscribe(final Subscriber subscriber){
        // При вызове данного метода подписчик должен удаляться из списка подписчиков.
        subscribers.remove(subscriber);
    }

    // Создайте метод startWork().
    public void startWork() {
        // Метод должен отправлять все статьи, которые хранятся в данный момент в списке articles, всем подписчикам из списка.
        for (Article article : articles){
            // Для отправки используйте метод send() класса Subscriber.
            for (Subscriber subscriber : subscribers){
                subscriber.send(article);
            }
        }
    }

    // Создайте метод publishNewArticle(). Метод принимает на вход объект класса Article.
    public void publishNewArticle(final Article article){
        // Метод должен добавлять новую статью в список статей articles, а затем рассылать её всем подписчикам из списка.
        articles.add(article);
        for (Subscriber subscriber : subscribers){
            subscriber.send(article);
        }

    }
}