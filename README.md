# 🔤 단어 찾기 (Word Search)

드래그로 숨겨진 단어를 찾는 게임. 한글·영어 각 **50단계**, 점수·콤보·최고기록 기능 포함.

## 웹에서 바로 하기

`index.html` 을 브라우저로 열면 됩니다. (PC 마우스 드래그 / 모바일 터치 모두 지원)

### 기능
- **난이도 50단계** — 레벨이 오를수록 격자가 커지고(8×8 → 16×16), 대각선·역방향이 추가되고, 단어가 길고 많아집니다(레벨당 6→14개). 매 게임마다 단어 위치는 랜덤 배치됩니다.
- **한글 / 영어** 전환 (🌐 버튼) — 단어와 UI가 함께 바뀝니다.
- **점수 시스템** — 단어 길이 × 100 × 콤보 배수. 연속으로 맞히면 🔥 콤보가 쌓이고, 헛드래그하면 콤보가 끊깁니다.
- **최고 기록** — 언어·레벨별로 브라우저에 저장됩니다(localStorage).
- 클리어한 레벨은 레벨 버튼에 초록 테두리로 표시됩니다.

## 📱 안드로이드 APK

로컬에 Android SDK가 없어도 **GitHub Actions가 클라우드에서 APK를 빌드**합니다.

### 자동 빌드 (권장)
1. 이 저장소를 GitHub에 push 합니다.
2. `main`/`master` 브랜치에 push되면 **Build Android APK** 워크플로우가 자동 실행됩니다.
   (또는 GitHub → **Actions** 탭 → **Build Android APK** → **Run workflow** 수동 실행)
3. 실행이 끝나면 해당 run 페이지 하단 **Artifacts → `word-search-debug-apk`** 를 다운로드 → 압축 풀면 `app-debug.apk`.
4. 안드로이드 기기에서 설치 (출처를 알 수 없는 앱 설치 허용 필요).

> 디버그 서명 APK라 별도 키스토어 없이 바로 설치/테스트 가능합니다. 스토어 배포용 릴리스 APK는 키스토어 서명이 필요합니다.

### 로컬에서 빌드하려면
Android Studio(또는 JDK 17 + Android SDK)가 설치돼 있어야 합니다.

```bash
# 게임 파일을 앱 assets로 복사
cp index.html android/app/src/main/assets/index.html

cd android
gradle wrapper --gradle-version 8.7   # 최초 1회 (wrapper 생성)
./gradlew assembleDebug
# 결과: android/app/build/outputs/apk/debug/app-debug.apk
```

## 구조
```
index.html                  # 게임 본체 (단일 파일, 단어 데이터/로직 전부 포함)
android/                    # 경량 WebView 안드로이드 앱 (androidx 의존성 없음)
  app/src/main/
    assets/index.html        # 빌드 시 위 index.html 사본
    java/.../MainActivity.java
.github/workflows/android.yml  # APK 자동 빌드 CI
```

> 단어를 수정하려면 `index.html` 상단의 `WORDS_DATA` 만 고치면 됩니다. CI 빌드 시 자동으로 앱에 반영됩니다.
