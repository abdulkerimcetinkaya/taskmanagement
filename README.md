# Görev Yönetim Sistemi

Bu proje, kullanıcıların görev oluşturmasına, görüntülemesine, güncellemesine ve silmesine olanak tanıyan basit bir Görev Yönetim Sistemi sunmaktadır. Spring Boot kullanılarak geliştirilmiş olan bu RESTful API, temel CRUD işlemlerini desteklemektedir.

## Özellikler

- **Görev Oluşturma:** Yeni görev ekleme.
- **Görevleri Listeleme:** Tüm görevleri listeleme.
- **Görev Detayları:** Belirli bir görevin detaylarını görüntüleme.
- **Görev Güncelleme:** Mevcut bir görevi güncelleme.
- **Görev Silme:** Belirli bir görevi silme.

## Kullanılan Teknolojiler

- **Backend:** Java 17, Spring Boot 3
- **Veritabanı:** H2 (Geliştirme ortamı için)

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

- **Geliştirme Ortamı:** Bu proje, eğitim amaçlı geliştirilmiş bir **mini projedir**. Gerçek bir üretim ortamında, daha sağlam ve sürdürülebilir bir yapı için **Data Transfer Object (DTO)** deseni, doğrulama (validation), logging, exception handling gibi iyileştirmeler eklenebilir.
- **İletişim:** Herhangi bir soru veya geri bildirim için [e-posta adresiniz] üzerinden benimle iletişime geçebilirsiniz.

---


