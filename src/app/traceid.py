import uuid


class TraceId:
    __trace_id = ""

    @staticmethod
    def set(value: str) -> None:
        def is_uuid(va: str) -> bool:
            try:
                uuid.UUID(va)
                return True
            except ValueError:
                return False

        if is_uuid(value):
            TraceId.__trace_id = value
        else:
            raise Exception("Não é possivel definir o Trace ID, o mesmo necessita ser um UUID válido")

    @staticmethod
    def get() -> str:
        return TraceId.__trace_id
