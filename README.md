# Görev Yönetim Sistemi

Bu proje, kullanıcıların görev oluşturmasına, görüntülemesine, güncellemesine ve silmesine olanak tanıyan basit bir Görev Yönetim Sistemi sunmaktadır. Spring Boot kullanılarak geliştirilmiş olan bu RESTful API, temel CRUD işlemlerini desteklemektedir.

## Özellikler

- **Görev Oluşturma:** Yeni görev ekleme.
- **Görevleri Listeleme:** Tüm görevleri listeleme.
- **Görev Detayları:** Belirli bir görevin detaylarını görüntüleme.
- **Görev Güncelleme:** Mevcut bir görevi güncelleme.
- **Görev Silme:** Belirli bir görevi silme.
- **DTO Deseni:** Data Transfer Object (DTO) kullanımı ile katmanlar arası veri transferi.
- **Doğrulama:** Girdi doğrulaması (validation) ile veri bütünlüğü.
- **Loglama:** SLF4J ile detaylı loglama.
- **Hata Yönetimi:** Merkezi exception handling ve uygun HTTP durum kodları.

## Kullanılan Teknolojiler

- **Backend:** Java 17, Spring Boot 3, Maven, Lombok
- **Veritabanı:** H2 (Geliştirme ortamı için)
- **Validation:** Jakarta Bean Validation (Hibernate Validator)
- **Loglama:** SLF4J / Logback

## Kurulum

1. **Depoyu Klonlayın:**
   ```bash
   git clone https://github.com/kullaniciadi/gorev-yonetim-sistemi.git
   ```
2. **Proje Dizinine Geçin:**
   ```bash
   cd gorev-yonetim-sistemi
   ```
3. **Gerekli Bağımlılıkları Yükleyin:**
   ```bash
   mvn clean install
   ```
4. **Uygulamayı Başlatın:**
   ```bash
   mvn spring-boot:run
   ```
   Uygulama varsayılan olarak `http://localhost:8080` adresinde çalışacaktır.

## API Kullanımı

### 1. Tüm Görevleri Getir

- **URL:** `GET /api/tasks`
- **Açıklama:** Tüm görevleri listeler.

### 2. Belirli Bir Görevi Getir

- **URL:** `GET /api/tasks/{id}`
- **Açıklama:** Belirtilen ID'ye sahip görevi getirir.

### 3. Yeni Görev Oluştur

- **URL:** `POST /api/tasks`
- **Açıklama:** Yeni bir görev oluşturur.
- **İstek Gövdesi:**
  ```json
  {
    "title": "Yeni Görev",
    "description": "Yeni görev açıklaması",
    "status": "IN_PROGRESS"
  }
  ```
- **Yanıt:** HTTP 201 Created
  ```json
  {
    "id": 1,
    "title": "Yeni Görev",
    "description": "Yeni görev açıklaması",
    "status": "IN_PROGRESS",
    "createdAt": "2024-01-01T10:00:00",
    "updatedAt": "2024-01-01T10:00:00"
  }
  ```
- **Doğrulama Hatası Yanıtı:** HTTP 400 Bad Request
  ```json
  {
    "timestamp": "2024-01-01T10:00:00",
    "message": "Validation failed",
    "errors": {
      "title": "Title is required",
      "status": "Status is required"
    }
  }
  ```

### 4. Görevi Güncelle

- **URL:** `PUT /api/tasks/{id}`
- **Açıklama:** Belirtilen ID'ye sahip görevi günceller.
- **İstek Gövdesi:**
  ```json
  {
    "title": "Güncellenmiş Görev",
    "description": "Güncellenmiş açıklama",
    "status": "COMPLETED"
  }
  ```

### 5. Görevi Sil

- **URL:** `DELETE /api/tasks/{id}`
- **Açıklama:** Belirtilen ID'ye sahip görevi siler.

## Notlar

- **Mimari Yapı:** Bu proje, profesyonel standartlarda geliştirilmiş olup, aşağıdaki best practice'leri içerir:
  - **DTO Pattern:** Controller ve Entity arasında veri transferi için DTO kullanımı.
  - **Validation:** Jakarta Bean Validation ile girdi doğrulaması.
  - **Logging:** SLF4J ile kapsamlı loglama.
  - **Exception Handling:** Merkezi hata yönetimi ve uygun HTTP durum kodları.
  - **Separation of Concerns:** Katmanlı mimari ile sorumlulukların ayrılması.
- **Geliştirme Ortamı:** H2 in-memory veritabanı kullanılmaktadır. Üretim ortamı için PostgreSQL, MySQL gibi veritabanları kullanılabilir.
- **İletişim:** Herhangi bir soru veya geri bildirim için "a.cetinkayaa78@gmail.com" üzerinden benimle iletişime geçebilirsiniz.

---


