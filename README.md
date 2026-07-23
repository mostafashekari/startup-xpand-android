# Startup Xpand — Android App

اپلیکیشن اندروید مدرن (Kotlin + Jetpack Compose) برای Startup Xpand.

## ساخت سریع APK (در Android Studio)

### ۱. باز کردن پروژه
1. Android Studio **Ladybug (2024.2)** یا جدیدتر را باز کن
2. **File → Open** → پوشه `StartupXpandApp` را انتخاب کن
3. صبر کن تا **Gradle Sync** تمام شود (اولین بار ممکن است چند دقیقه طول بکشد چون وابستگی‌ها دانلود می‌شوند)

### ۲. ساخت APK دیباگ (تست)
- منوی بالا: **Build → Build Bundle(s) / APK(s) → Build APK(s)**
- بعد از اتمام، روی لینک **locate** کلیک کن
- فایل در مسیر زیر ساخته می‌شود:
  ```
  app/build/outputs/apk/debug/app-debug.apk
  ```

### ۳. ساخت APK انتشار (Release)
1. **Build → Generate Signed Bundle / APK**
2. APK را انتخاب کن → Next
3. اگر کلید ندارید: **Create new...** و یک keystore بساز
4. build type: **release** → Finish
5. خروجی:
  ```
  app/build/outputs/apk/release/app-release.apk
  ```

### ۴. نصب روی گوشی
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```
یا فایل را مستقیم به گوشی کپی و نصب کن.

## نیازمندی‌ها
| مورد | حداقل |
|------|--------|
| Android Studio | 2024.2+ |
| JDK | 17 |
| minSdk | 26 (Android 8.0) |
| targetSdk | 35 |

## ساختار
```
app/src/main/java/com/startupxpand/app/
├── MainActivity.kt
├── data/           # مدل‌ها + داده‌های واقعی از بکاپ وب
├── navigation/     # Bottom Nav + NavHost
└── ui/
    ├── theme/      # رنگ‌های نئونی دارک
    ├── components/ # GlassCard, GradientButton, ...
    └── screens/    # Home / Ideas / Countries / Stories / FAQ / Assessment
```

ساخته‌شده بر اساس سورس وب Startup Xpand.
