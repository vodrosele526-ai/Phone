# Phone — GitHub APK Build Project

## GitHub-এ আপলোড
1. ZIP extract করুন।
2. GitHub-এর Phone repository খুলুন।
3. Add file → Upload files চাপুন।
4. Extract করা ফোল্ডারের ভেতরের সব ফাইল ও ফোল্ডার upload area-তে দিন। `.github` ফোল্ডারটিও জরুরি।
5. Commit changes চাপুন।

## APK Build
1. Repository-তে Actions tab খুলুন।
2. Build Phone APK workflow নির্বাচন করুন।
3. Run workflow চাপুন (push করলেও build শুরু হবে)।
4. সফল run খুলে Artifacts থেকে Phone-debug-apk ডাউনলোড করুন।
5. ZIP extract করে app-debug.apk ফোনে install করুন।

## সতর্কতা
এটি প্রথম starter build। ডায়ালার UI, কল শুরু করার প্রাথমিক ব্যবস্থা, default dialer role request এবং Secret Panel placeholder আছে। Contacts/Call History-এর নিজস্ব UI, পূর্ণ incoming/outgoing call controls, PIN verification, fingerprint এবং কার্যকর app hiding এখনো তৈরি হয়নি।
Android সাধারণ অ্যাপকে অন্য অ্যাপের আইকন সিস্টেম-জুড়ে পুরোপুরি লুকাতে দেয় না; Android/launcher অনুযায়ী সীমাবদ্ধতা আছে। কলের জন্য SIM, permission ও Android Telecom support দরকার।
Secret Panel demo code: *#*#786#*#* — ডায়াল করে Call চাপুন।
