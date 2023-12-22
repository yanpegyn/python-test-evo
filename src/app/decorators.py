from src.app.traceid import TraceId


def auto_set_trace_id(func):
    def auto_set_trace_id_wrapper(*args, **kwargs):
        if func.__name__ == "lambda_handler":
            event = args[0]
            context = args[1]
            if TraceId.get() == "":
                traceid = event.get("_traceid", "")
                if traceid != "":
                    TraceId.set(traceid)
        else:
            raise TypeError("Este decorator só deve ser usado na definição do lambda_handler")
        return func(*args, **kwargs)

    return auto_set_trace_id_wrapper
