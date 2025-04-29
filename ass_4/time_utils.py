import time

def format_hhmm_ss(ts):
    return time.strftime("%H:%M:%S", time.localtime(ts))
