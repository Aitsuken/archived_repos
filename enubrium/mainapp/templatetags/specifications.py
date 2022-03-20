from django import template

register = template.Library()


TABLE_HEAD = """
                <table class="table">
                <tbody>   
                
             """

TABLE_TAIL = """
                </tbody>
                </table>

            """

TABLE_CONTENT = """
                    <tr>
                        <td>{ name }</td>
                        <td>{ value }</td>
                    </tr>
                """

PRODUCT_SPEC = {
    'notebook': {
        'диагональ': 'diagonal',
        'тип дисплея': 'display_type',
        'частота процессора': 'processor_freq',
        'оперативная память': 'ram',
        'видеокарта': 'video',
        'время работы' : 'time_without_charge',
    },
    'smartphone': {
        'диагональ': 'diagonal',
        'тип дисплея': 'display_type',
        'разрешение экрана': 'resolution',
        'обьем батареи': 'accum_value',
        'оперативная память': 'ram',
        'максимальный обьём встраиваемой памяти' : 'sd',
        'главная камера': 'main_cam',
        'фронтальная камера': 'frontal_cam',

    }
}

def get_product_spec(product, model_name):
    table_content = ''
    for name, value in  PRODUCT_SPEC[model_name].items():
        table_content += TABLE_CONTENT.format(name=name, value=getattr(product, value))
    return table_content

@register.filter
def product_spec(product):
    model_name = product.__class__._meta.model_name

    pass