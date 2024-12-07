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


## **Product Service - Ürün Servisi**

1. **Create Product - Ürün Oluştur**

   Request: (POST) http://localhost:8080/products

2. **Update Product - Ürünü Güncelle**

   Request: (PUT) http://localhost:8080/products/{productId}

3. **Delete Product - Ürünü Sil**

   Request: (DELETE) http://localhost:8080/products/{productId}

4. **Get Product By Id - ID'si Verilmiş Ürünü Görüntüle**

   Request: (GET) http://localhost:8080/products/{productId}

5. **Get All Products - Tüm Ürünleri Görüntüle**

   Request: (GET) http://localhost:8080/products


## **Cart Service - Sepet Servisi**

1. **Add Product To Cart - Sepete Ürün Ekle**

   Request: (POST) http://localhost:8080/carts/item/{productId}

2. **Remove Product From Cart - Sepetteki Ürünü Sil**

   Request: (DELETE) http://localhost:8080/carts/item/{productId}

3. **Epmty Cart - Sepeti Boşalt**

   Request: (PATCH) http://localhost:8080/carts

4. **Get Cart - Sepeti Görüntüle**

   Request: (GET) http://localhost:8080/carts

5. **Add Quantity To Cart Item - Sepetteki Ürün Miktarını Arttır**

   Request: (PATCH) http://localhost:8080/carts/item/add-quantity/{productId}

6. **Remove Quantity From Cart Item - Sepetteki Ürün Miktarını Azalt**

   Request: (PATCH) http://localhost:8080/carts/item/remove-quantity/{productId}


## Order Service - Sipariş Servisi

1. **Place Order - Sipariş Ver**

   Request: (POST) http://localhost:8080/orders

2. **Get Order - Siparişi Görüntüle**

   Request: (GET) http://localhost:8080/orders/{orderId}

3. **Get All Orders - Tüm Siparişleri Görüntüle**

   Request: (GET) http://localhost:8080/orders


## Auth Service - Kimlik Doğrulama Servisi

1. **Login - Giriş Yap**

   Request: (POST) http://localhost:8080/auth/login

2. **Me - Kendimi Bilgilerimi Göster**

   Request: (GET) http://localhost:8080/auth/me
   

