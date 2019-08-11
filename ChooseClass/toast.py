
from win10toast import ToastNotifier
import sys

print('start to toast...')
title = str(sys.argv[1])
context = str(sys.argv[2])
time = int(sys.argv[3])
_toaster = ToastNotifier()
_toaster.show_toast(title, context, None, time, True)
print("toast in process...")
