xml中设置页面进入动画 enterAnim 和 popEnterAnim 的区别

- editText设置只能输入单行：inputType="text"
- 命名全部以模块名称开头


---
一个1920X1080的图片放到drawable目录下，将它解析成drawable或者bitmap，这个时候，drawable或者bitmap的宽度都是5040，
而放在xxhdpi-drawable下，则宽度都是1260
用BitmapFactory.options获取到的是正常值

---
drawable是抽象概念
bitmap是其存在的一个实体
通过bitmapDrawable将drawable和bitmap联系起来
Drawable.drawableFromBitmap()将Bitmap转为Drawable
BitmapDrawable.getBitmap()将Drawable转化为Bitmap

Bitmap储存的是像素信息，
Drawable储存的是对Canvas的一系列操作。
而BitmapDrawable储存的是*把Bitmap渲染到Canvas上*这个操作。