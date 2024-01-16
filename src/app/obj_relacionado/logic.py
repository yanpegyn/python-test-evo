from src.app.obj_relacionado.acoplated_object import AcoplatedObject


def main(event, context):
    acoplated = AcoplatedObject(simple_value=event['simple_value'])
    result = {
        "obj_value_list": []
    }
    for obj in acoplated.simple_objects:
        result["obj_value_list"].append(obj.a)
    return result
