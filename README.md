# TabDemo
Small project that demonstrates Nested Fragments in a ViewPager working through rotations using API 23, but crashing in API 24/25

## API 23 (default)

Run app, rotate app, notice fragments are retained and app works.

## API 25

Edit build.gradle:
* change '''def sdk = 25'''
* comment out '''compile "com.android.support:appcompat-v7:$workingSupportLib"'''
* uncomment '''compile "com.android.support:appcompat-v7:$brokenSupportLib"'''

Run app, rotate app, crash!
