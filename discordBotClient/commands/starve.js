const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');
require('dotenv').config();

module.exports = {
    data: new SlashCommandBuilder()
        .setName('starve')
        .setDescription('starve specific player on server')
        .addStringOption(option => option.setName('target').setDescription('target to kill')),
    async execute(interaction) {
        const target = interaction.options.get('target').value;
         axios.get(`${process.env.API}${process.env.PORT}/starve`, {
            params: {
                target: target
            }
         }
        ).then(function (response) {
            interaction.reply(response.data);
        }).catch(function (error) {
            console.log(error);
            interaction.reply("Something went wrong");
          })
        return
    },
};