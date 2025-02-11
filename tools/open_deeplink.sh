adb shell "am start -a android.intent.action.VIEW -d $(echo $1 | sed 's/&/\\&/g') com.michel.weatherit"
