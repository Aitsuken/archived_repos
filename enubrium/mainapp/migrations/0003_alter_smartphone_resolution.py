# Generated by Django 3.2.2 on 2021-05-30 19:56

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('mainapp', '0002_notebook_smartphone'),
    ]

    operations = [
        migrations.AlterField(
            model_name='smartphone',
            name='resolution',
            field=models.CharField(max_length=255, verbose_name='разрешение экрана'),
        ),
    ]
