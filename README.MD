# **Project Documentation - Proje Dökümanı**

Müşterilerin sepetine ürün ekleme,
çıkarma ve miktar güncelleme işlemlerinde toplam fiyatı otomatik 
olarak hesaplayan, sipariş geçildikten sonra, ürün fiyatı değişse
bile müşterinin kendi satın aldığı fiyatı görebildiği, ayrıca ürünlerin stok takibi
yapılabilen ve stok bittiğinde sipariş verilmesi engellenen Spring Boot Projesi

Proje içerisinde Authentication için JWT token kullandım. Müşteriler sisteme login olduğunda
bir token'a sahip olur ve bu token aracılığıyla diğer operasyonlara izin verilir.

Anemic Domain Model kullanarak business işlerini servicelerde yapmak yerine,
Rich Domain Model kullanarak iş yükünün çoğunu domaine aldım. Bu sayede projede
daha az kod tekrarı, bakım kolaylığı ve daha güçlü okunabilirlik sağladım. 


## **Proje Dosya Yapısı (Project Structure)**

```plaintext
enoca-java-challenge/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── furkan/
│   │   │           └── enoca/
│   │   │               ├── advice/           # Global Exception yakalayıcı
│   │   │               ├── controller/       # API Controller sınıfları
│   │   │               ├── exception/        # Custom Exception sınıfları
│   │   │               ├── model/            # Entity sınıfları
│   │   │               │   ├── dto/                # DTO (Data Transfer Object) sınıfları
│   │   │               │   ├── entity/             # Entity sınıfları
│   │   │               │   └── enums/              # Enum sınıfları
│   │   │               ├── repository/       # JPA Repository sınıfları
│   │   │               ├── service/          # Servis sınıfları (iş mantığı)
│   │   │               ├── service/impl/     # Servis implementasyonları
│   │   │               └── EnocaApplication.java  # Ana sınıf (main method)
│   │   │
│   │   ├── resources/
│   │   │   ├── static/                       
│   │   │   ├── templates/                     
│   │   │   └── application.properties        # Uygulama ayarları (Veritabanı Bağlantıları)
│   │
├── .gitignore                               # Git tarafından yok sayılacak dosyalar
├── mvnw                                     # Maven Wrapper (Windows için)
├── mvnw.cmd                                 # Maven Wrapper (Linux/Mac için)
├── pom.xml                                  # Maven bağımlılıkları
└── README.md                                # Proje dokümantasyonu
```


# **Services - Servisler**

1. **User Service**
2. **Customer Service**
3. **Product Service**
4. **Cart Service**
5. **Order Service**
6. **Auth Service**


## **Customer Service - Müşteri Servisi**

1. **Create Customer - Müşteri Oluştur**

   Request: (POST) http://localhost:8080/customers

   
İsim, Soyisim, Email, Telefon ve Şifre bilgileri ile bir müşteri oluşturur.
Müşterinin UUID tipinde ID, İsim, Soyisim, Mail ve Telefon bilgilerini geri döner.

## **Product Service - Ürün Servisi**

1. **Create Product - Ürün Oluştur**

   Request: (POST) http://localhost:8080/products

İsim, Fiyat, Açıklama, Kod ve Stok bilgileriyle bir ürün oluşturur.
Ürünün UUID tipinde ID, İsim, Fiyat, Açıklama, Kod, Stok, Oluşturulma ve Güncellenme Tarihi bilgilerini geri döner.

2. **Update Product - Ürünü Güncelle**

   Request: (PUT) http://localhost:8080/products/{productId}

ID'si verilen ürünü İsim, Fiyat, Açıklama, Kod ve Stok bilgileriyle birlikte günceller.
Ürünün UUID tipinde ID, İsim, Fiyat, Açıklama, Kod, Stok, Oluşturulma ve Güncellenme Tarihi bilgilerini geri döner.

3. **Delete Product - Ürünü Sil**

   Request: (DELETE) http://localhost:8080/products/{productId}

ID'si verilen ürünü siler.

4. **Get Product By Id - ID'si Verilmiş Ürünü Görüntüle**

   Request: (GET) http://localhost:8080/products/{productId}

ID'si verilen ürünün UUID tipinde ID, İsim, Fiyat, Açıklama, Kod, Stok, Oluşturulma ve Güncellenme Tarihi bilgilerini geri döner.

5. **Get All Products - Tüm Ürünleri Görüntüle**

   Request: (GET) http://localhost:8080/products

Tüm ürünleri UUID tipinde ID, İsim, Fiyat, Açıklama, Kod, Stok, Oluşturulma ve Güncellenme Tarihi bilgileriyle birlikte geri döner.

## **Cart Service - Sepet Servisi**

1. **Add Product To Cart - Sepete Ürün Ekle**

   Request: (POST) http://localhost:8080/carts/item/{productId}

ID'si verilen ürünü, Müşterinin sepetine ekler ve sepet fiyatını günceller. Geriye sadece http response 200 OK döner.

2. **Remove Product From Cart - Sepetteki Ürünü Sil**

   Request: (DELETE) http://localhost:8080/carts/item/{productId}

ID'si verilen ürünün deleted_at fieldini doldurur, sepetten çıkarır ve sepet fiyatını günceller. Geriye sadece http response 200 OK döner.

3. **Epmty Cart - Sepeti Boşalt**

   Request: (PATCH) http://localhost:8080/carts

Sepetteki tüm ürünleri sepetten çıkarır ve sepet fiyatını sıfırlar.

4. **Get Cart - Sepeti Görüntüle**

   Request: (GET) http://localhost:8080/carts

Sepet içeriğini görüntüler.

5. **Add Quantity To Cart Item - Sepetteki Ürün Miktarını Arttır**

   Request: (PATCH) http://localhost:8080/carts/item/add-quantity/{productId}

ID'si verilen ürünün sepetteki miktarını arttırır. Sepet fiyatını günceller. Geriye sadece http response 200 OK döner.

6. **Remove Quantity From Cart Item - Sepetteki Ürün Miktarını Azalt**

   Request: (PATCH) http://localhost:8080/carts/item/remove-quantity/{productId}

ID'si verilen ürünün sepetteki miktarını azaltır. Geriye sadece http response 200 OK döner.

## Order Service - Sipariş Servisi

1. **Place Order - Sipariş Ver**

   Request: (POST) http://localhost:8080/orders

İstek gönderildiğinde sepet içeriğini de barındıran bir sipariş oluşturur. Geriye Sipariş ID'sini, statusu, müşteriyi ve sepet içeriğini döner.

2. **Get Order - Siparişi Görüntüle**

   Request: (GET) http://localhost:8080/orders/{orderId}

ID'si verilen siparişin ID'si, status bilgisi sepet ve müşteri bilgileri ile birlikte görüntüler. 

3. **Get All Orders - Tüm Siparişleri Görüntüle**

   Request: (GET) http://localhost:8080/orders

Müşteriye ait tüm siparişleri getirir.


## Auth Service - Kimlik Doğrulama Servisi

1. **Login - Giriş Yap**

   Request: (POST) http://localhost:8080/auth/login

Email ve password bilgileriyle birlikte kullanıcı giriş yapar. Kullanıcı ID, İsim, Soyisim, Email ve Token bilgilerini geri döner.

2. **Me - Kendi Bilgilerimi Göster**

   Request: (GET) http://localhost:8080/auth/me

Kullanıcıya kendi ID, isim, soyisim ve mail bilgilerini geri döner.


## **Postman İstekleri**
![Postmandan Atılan Istekler](C:\Users\furka\OneDrive\PostmanRequests.png)   

