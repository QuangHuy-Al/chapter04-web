# Chapter 04 — Survey

Ứng dụng HTML/CSS, Servlet và JSP chạy trên Java 17 và Tomcat 10.1.

Luồng xử lý: `index.html → SurveyServlet → Survey → SurveyDTO → survey-result.jsp`.

## Chạy bằng Docker

Tại thư mục dự án, khi đã cài và mở Docker Desktop:

```powershell
docker build -t chapter04-web .
docker run --rm -p 8080:8080 --name chapter04-web chapter04-web
```

Mở <http://localhost:8080/>. Docker tự build WAR bằng Maven và triển khai thành `ROOT.war` trên Tomcat 10.1, nên không thêm `/chapter04-web` vào URL.

Để thử cổng tùy chỉnh giống Render:

```powershell
docker run --rm -e PORT=10000 -p 8080:10000 --name chapter04-web chapter04-web
```

Vẫn mở <http://localhost:8080/>. `PORT` là cổng bên trong container; mặc định là `8080` nếu không được cung cấp.

## Đưa lên GitHub

1. Tạo repository trống trên GitHub, ví dụ `chapter04-web`, không tạo sẵn README hoặc .gitignore.
2. Tại thư mục dự án, chạy các lệnh dưới đây. Thay `YOUR_USERNAME` bằng tài khoản GitHub của bạn:

```powershell
git init -b main
git add .
git commit -m "Add survey app with Docker and Render configuration"
git remote add origin https://github.com/YOUR_USERNAME/chapter04-web.git
git push -u origin main
```

Nếu Git yêu cầu thông tin tác giả, đặt `git config user.name "Tên của bạn"` và `git config user.email "Email GitHub của bạn"`, rồi chạy lại lệnh commit. Hoàn tất đăng nhập GitHub nếu Git yêu cầu.

Các file cần có trên GitHub: `src/`, `pom.xml`, `Dockerfile`, `.dockerignore`, `.gitignore`, `render.yaml`, `README.md`. `.gitignore` loại bỏ `target/`, `.idea/`, `.smarttomcat/` và các cấu hình riêng trên máy. Không cần đưa WAR lên GitHub.

## Triển khai Render bằng Blueprint

1. Mở [Render Dashboard](https://dashboard.render.com/).
2. Chọn **New → Blueprint**, kết nối GitHub và chọn repository vừa tạo, nhánh `main`.
3. Render đọc `render.yaml`. Kiểm tra service `chapter04-web`, runtime Docker và plan Free, rồi chọn **Deploy Blueprint**.
4. Theo dõi log build/deploy. Khi service ở trạng thái Live, mở URL `https://<ten-service>.onrender.com/` mà Render cung cấp.

Không cần nhập Build Command hoặc Start Command: Dockerfile đã định nghĩa cả hai. `healthCheckPath` là `/index.html`. Khi push commit mới lên nhánh đã liên kết, cấu hình này yêu cầu Render tự triển khai lại.

Nếu tạo thủ công bằng **New → Web Service**: chọn repository, Language **Docker**, Dockerfile Path `./Dockerfile`, Docker Build Context `.`, Health Check Path `/index.html` và chọn plan phù hợp. Dùng cách này thì `render.yaml` không tự được áp dụng.

Render cung cấp biến `PORT`; Dockerfile chuyển biến này thành Java system property `http.port`, để Tomcat lắng nghe trên cổng đúng và mọi địa chỉ mạng. Không cần đặt `PORT` thủ công trên Render.

Ứng dụng giữ kết quả trong session, không dùng database. Session có thể mất khi service khởi động lại hoặc triển khai bản mới; dữ liệu không được lưu lâu dài.

Tài liệu chính thức: [Docker trên Render](https://render.com/docs/docker), [Blueprint YAML](https://render.com/docs/blueprint-spec), [cổng HTTP của Web Service](https://render.com/docs/web-services#port-binding).

## Chạy trực tiếp trên Tomcat

```powershell
mvn clean package
```

Chép `target/chapter04-web.war` vào `webapps` của Tomcat 10.1 rồi khởi động Tomcat. Với cách này, mở <http://localhost:8080/chapter04-web/>.
