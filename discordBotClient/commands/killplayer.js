const { SlashCommandBuilder } = require('@discordjs/builders');
const axios = require('axios');

module.exports = {
    data: new SlashCommandBuilder()
        .setName('kill')
        .setDescription('kill specific player on server')
        .addStringOption(option => option.setName('target').setDescription('target to kill')),
    async execute(interaction) {
        const target = interaction.options.get('target').value;
         axios.get('http://127.0.0.1:8001/kill', {
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